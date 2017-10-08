package com.gwmobile.metroexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.LinearLayout
import com.gwmobile.metroexplorer.manager.FetchMetroStationsManager
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_metro_station.*
import android.support.v4.widget.SearchViewCompat.setSearchableInfo
import android.support.v4.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import android.app.SearchManager
import android.content.Context
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import com.gwmobile.metroexplorer.R.id.game_toolbar
import com.gwmobile.metroexplorer.R.id.rv_station_list
import org.jetbrains.anko.toast


class MetroStationActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_station)
        setSupportActionBar(game_toolbar)

        val recyclerView = rv_station_list
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        FetchMetroStationsManager.getMetroStationList(this, recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.metro, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
//        var queryMetro = SearchManager.QUERY
//        toast(queryMetro)
        return true
    }



}
