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
    lateinit var itemClickListener: OnItemClickListener

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

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.stationHolder.setOnClickListener(this)
        }

        fun bindItems(station: Station){
            val name = itemView.stationNameHolder.stationName
            name.text = station.name
        }

        override fun onClick(view: View) = itemClickListener.onItemClick(itemView, adapterPosition)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}