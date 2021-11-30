package com.example.administrator.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.R

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infomation)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Infomation"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
