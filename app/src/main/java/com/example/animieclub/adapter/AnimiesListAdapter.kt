package com.example.animieclub.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animieclub.R
import com.example.animieclub.model.GetAnimieListData
import com.example.animieclub.view.AnimieDetailsActivity
import com.squareup.picasso.Picasso

class AnimiesListAdapter (context: Context, data: List<GetAnimieListData>?) : RecyclerView.Adapter<AnimiesListAdapter.ViewHolder>() {

    private var context : Context
    var data:List<GetAnimieListData>


    init {
        this.context=context
        this.data= data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimiesListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.animie_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: AnimiesListAdapter.ViewHolder, position: Int) {

        holder.animieDuration.text="Episodes : "+data[position].episodes.toString()
        holder.animieTitle.text=data[position].titleEnglish.toString()


        Picasso.get().load(data[position].images?.webp?.imageUrl.toString()).into(holder.animieImage)

        holder.animieLL.setOnClickListener {
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(data[position].url)
//            context.startActivity(openURL)
            context.startActivity(Intent(context,AnimieDetailsActivity::class.java).putExtra("id",data[position].malId.toString()))
        }

//        holder.newsContent.setSelected(true);
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var animieDuration : TextView = itemView.findViewById(R.id.animieDuration)
        var animieTitle : TextView = itemView.findViewById(R.id.animieTitle)
        var animieImage : ImageView = itemView.findViewById(R.id.animieImage)
        var animieLL : LinearLayout = itemView.findViewById(R.id.animieLL)

    }


}