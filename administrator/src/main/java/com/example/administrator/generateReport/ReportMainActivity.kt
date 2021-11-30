package com.example.administrator.generateReport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReportMainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportmain)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Generate Report"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Reports")



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
