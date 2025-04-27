package com.example.ghithubrepo.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ghithubrepo.R
import com.example.ghithubrepo.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val RC_SIGN_IN = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Check if the user is already signed in via FirebaseAuth
        if (auth.currentUser != null) {
            goToMain()
        } else {
            val sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
            val email = sharedPref.getString("EMAIL", null)

            if (!email.isNullOrEmpty()) {
                // User is already logged in, go to MainActivity
                goToMain()
            }
        }

        // Set up Google Sign-In options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Replace with your ID token
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signinTv.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
//        binding.main.visibility = View.GONE
        binding.loader.visibility = View.VISIBLE
        binding.spinKit.isActivated = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: Exception) {
                binding.main.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE


                Toast.makeText(this, "Google Sign-In failed: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val email = user?.email?.substringBefore("@")

                    email?.let {
                        val sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
                        sharedPref.edit().putString("EMAIL", it).apply()
                    }

                    goToMain()

                } else {
                    binding.loader.visibility = View.GONE
                    binding.main.visibility = View.VISIBLE
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
