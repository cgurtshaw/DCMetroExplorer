package com.gwmobile.metroexplorer

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class LandmarkDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_PARAM_ID = "landmark_id"
        fun newIntent(context: Context, position: Int): Intent {
            val intent = Intent(context, LandmarkDetailActivity::class.java)
            intent.putExtra(EXTRA_PARAM_ID, position)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_detail)
    }
}
