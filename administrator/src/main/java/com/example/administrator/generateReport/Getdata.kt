package com.example.administrator.generateReport

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_getdata.*


class Getdata : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getdata)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Case Reports"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Reports")
        getData()

    }


    private fun getData() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var list = ArrayList<DatabaseModel>()
                for (data in snapshot.children) {

                    val model = data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)

                    if (list.size > 0) {

                        val adapter = DataAdapter(list)
                        recyclerview.adapter = adapter
                    }
                }

            }

        })
    }


        override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }

}