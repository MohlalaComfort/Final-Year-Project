package com.example.epolicemanagementsystem.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.epolicemanagementsystem.R

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

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
