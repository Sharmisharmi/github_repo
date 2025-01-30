package com.example.animieclub

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
import com.example.animieclub.model.GetAnimieListData
import com.squareup.picasso.Picasso

class ImageAdapter(context: Context, data: ArrayList<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var context : Context
    var data:List<String>


    init {
        this.context=context
        this.data= data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.animie_list_carousel,parent,false))
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {



        Picasso.get().load(data[position]).into(holder.animieImage)


    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){


        var animieImage : ImageView = itemView.findViewById(R.id.list_item_image)

    }


}
