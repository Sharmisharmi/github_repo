package com.example.ghithubrepo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ghithubrepo.R
import com.example.ghithubrepo.databinding.ActivityMainBinding
import com.example.ghithubrepo.databinding.ActivitySettingsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signOutTv.setOnClickListener {
           signOut()
        }



    }
    private fun signOut() {
        // Sign out from Firebase
        FirebaseAuth.getInstance().signOut()

        // Sign out from Google
        GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
            .signOut()
            .addOnCompleteListener {
                // Clear SharedPreferences
                val sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                // Go back to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}