package com.gwmobile.metroexplorer


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gwmobile.metroexplorer.model.Landmark
import com.gwmobile.metroexplorer.model.LandmarkList
import com.gwmobile.metroexplorer.model.Station
import kotlinx.android.synthetic.main.row_places.view.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_landmark.view.*
import kotlinx.android.synthetic.main.row_station.view.*

class LandmarksAdapter(val landmarkList: LandmarkList, val context: Context):
        RecyclerView.Adapter<LandmarksAdapter.ViewHolder>(){

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
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(landmark: Landmark){
            itemView.landmark_name.text = landmark.name
        }
    }

}