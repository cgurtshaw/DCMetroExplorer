package com.gwmobile.metroexplorer


import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.gwmobile.metroexplorer.model.Landmark
import com.gwmobile.metroexplorer.model.LandmarkList
import com.gwmobile.metroexplorer.model.Station
import kotlinx.android.synthetic.main.row_places.view.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_landmark.view.*
import kotlinx.android.synthetic.main.row_station.view.*

class LandmarksAdapter(val landmarkList: LandmarkList, val context: Context):
        RecyclerView.Adapter<LandmarksAdapter.ViewHolder>(){

    lateinit var itemClickListener: OnItemClickListener

    override fun getItemCount() : Int{
        return landmarkList.landmarks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_landmark, parent,
                false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LandmarksAdapter.ViewHolder, position: Int) {
        holder.bindItems(landmarkList.landmarks[position])
        if(landmarkList.landmarks[position].imageURL.isNotEmpty()){
            Picasso.with(context)
                    .load(landmarkList.landmarks[position].imageURL)
                    .into(holder.itemView.landmarkImage)
        }
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        init{
            itemView.landmark_holder.setOnClickListener(this)
        }

        fun bindItems(landmark: Landmark){
            itemView.landmark_name.text = landmark.name
        }

        override fun onClick(view: View){
            itemClickListener.onItemClick(context, itemView, adapterPosition)

        }
    }

    interface OnItemClickListener{
        fun onItemClick(context: Context, view: View, position: Int)
    }

}