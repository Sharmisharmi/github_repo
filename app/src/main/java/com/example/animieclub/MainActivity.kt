package com.example.animieclub

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animieclub.adapter.AnimiesListAdapter
import com.example.animieclub.databinding.ActivityMainBinding
import com.example.animieclub.model.GetAnimieListResponse
import com.example.animieclub.view_model.ViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val viewModel = ViewModel()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAnimieAPI()

    
    }

    private fun getAnimieAPI() {
        viewModel!!.getAnimieList()
            .observe(this) { res: GetAnimieListResponse ->


                val images:ArrayList<String> = ArrayList()
                for(i in 0..9) {



                    images.add(res.data?.get(i)?.images!!.webp?.imageUrl.toString())

                }

                binding.animiesRecyclerView.visibility= View.VISIBLE
                binding.animiesRecyclerView.setHasFixedSize(true)
                binding.animiesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
                binding.animiesRecyclerView.adapter = AnimiesListAdapter(this, res.data)


//                    binding.textView.text = res.data?.get(0)?.titles?.get(0)?.title.toString()


                sendToCarousel(images)

            }

    }

    private fun sendToCarousel(images: ArrayList<String>) {



        val adapter: ImageAdapter = ImageAdapter(this@MainActivity, images)
        binding.recycler.adapter = adapter

//        adapter.setOnItemClickListener(object : OnItemClickListener() {
//            fun onClick(imageView: ImageView?, path: String?) {
//                //Do something like opening the image in new activity or showing it in full screen or something else.
//            }
//        })

    }
}