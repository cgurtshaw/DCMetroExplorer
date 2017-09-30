package com.gwmobile.metroexplorer

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.gwmobile.metroexplorer.model.Station
import com.gwmobile.metroexplorer.model.StationList
import kotlinx.android.synthetic.main.row_station.view.*


/**
 * Created by cgurtshaw on 9/27/2017.
 */

class MetroStationsAdapter(val stationList: StationList, val context: Context) : RecyclerView.Adapter<MetroStationsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return stationList.stations.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_station, parent,
                false)
        return ViewHolder(itemView, context)
    }

    override fun onBindViewHolder(holder: MetroStationsAdapter.ViewHolder, position: Int) {
        holder.bindItems(stationList.stations[position])
    }

    inner class ViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(station: Station){
            itemView.station_name.text = station.name
            clearMetroLines()
            addMetroLines(station)
        }

        fun addMetroLines(station: Station) {
            for(line: String in station.lines.iterator()){
                when(line){
                    "RD" -> itemView.metro_lines.image_red_line.visibility = View.VISIBLE
                    "BL" -> itemView.metro_lines.image_blue_line.visibility = View.VISIBLE
                    "OR" -> itemView.metro_lines.image_orange_line.visibility = View.VISIBLE
                    "SV" -> itemView.metro_lines.image_silver_line.visibility = View.VISIBLE
                    "GR" -> itemView.metro_lines.image_green_line.visibility = View.VISIBLE
                    "YL" -> itemView.metro_lines.image_yellow_line.visibility = View.VISIBLE
                }
            }
        }

        fun clearMetroLines(){
            itemView.metro_lines.image_red_line.visibility = View.GONE
            itemView.metro_lines.image_blue_line.visibility = View.GONE
            itemView.metro_lines.image_orange_line.visibility = View.GONE
            itemView.metro_lines.image_silver_line.visibility = View.GONE
            itemView.metro_lines.image_green_line.visibility = View.GONE
            itemView.metro_lines.image_yellow_line.visibility = View.GONE
        }

    }

}