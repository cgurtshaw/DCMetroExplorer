package com.gwmobile.metroexplorer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gwmobile.metroexplorer.model.Station
import com.gwmobile.metroexplorer.model.StationList
import kotlinx.android.synthetic.main.row_station.view.*


/**
 * Created by cgurtshaw on 9/27/2017.
 */

class MetroStationsAdapter(val stationList: StationList) : RecyclerView.Adapter<MetroStationsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return stationList.stations.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_station, parent,
                false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MetroStationsAdapter.ViewHolder, position: Int) {
        holder.bindItems(stationList.stations[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(station: Station){
            val name = itemView.stationNameHolder.stationName
            name.text = station.name
        }

    }

}