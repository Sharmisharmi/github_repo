package com.example.animieclub.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.animieclub.databinding.ActivityAnimieDetailsBinding
import com.example.animieclub.model.GetAnimieListData
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsData
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsResponse
import com.example.animieclub.view_model.ViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener



class AnimieDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityAnimieDetailsBinding

    var get_id = ""

    val viewModel = ViewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        get_id = intent.getStringExtra("id").toString()

        Log.d("get_mall_id", "onCreate: "+get_id)
        getAnimieDetailsAPI(get_id)



    }

    private fun getAnimieDetailsAPI(id: String) {

            viewModel.getAnimieDetails(id.toInt())
                .observe(this) { res: GetAnimieDetailsResponse ->

                    binding.titleTv.text = res.data?.titleEnglish?.toString()
                    binding.synopsisTv.text = res.data?.synopsis?.toString()
                    binding.episodeTv.text ="Episodes : "+ res.data?.episodes.toString() + " | " + res.data?.duration.toString().split(" ").toTypedArray()[0] +" mins" + " | Score : " +res.data?.score.toString()

                    var youtubeId = res.data?.trailer?.youtubeId.toString()

                    lifecycle.addObserver(binding.youtubePlayerView)

                    // below method will provides us the youtube player ui controller such
                    // as to play and pause a video to forward a video and many more features.



                    // adding listener for our youtube player view.
                    binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            // loading the selected video into the YouTube Player
                            youTubePlayer.loadVideo(youtubeId, 0f)
                        }

                        override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                            // this method is called if video has ended,
                            super.onStateChange(youTubePlayer, state)
                        }
                    })


                }

        }

    }
