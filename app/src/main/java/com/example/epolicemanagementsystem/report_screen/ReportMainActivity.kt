package com.example.epolicemanagementsystem.report_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.epolicemanagementsystem.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_reportmain.*

class ReportMainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportmain)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Report Case"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Reports")
        report_btn.setOnClickListener {
            sendData()
        }
    }

    private fun sendData() {
        var idNo = et_name.text.toString().trim()
        var name_surname = et_email.text.toString().trim()
        var contacts = et_email2.text.toString().trim()
        var description = tv_description.text.toString().trim()

        if (idNo.isNotEmpty() && name_surname.isNotEmpty() && contacts.isNotEmpty() && description.isNotEmpty()) {

            var model = DatabaseModel(idNo, name_surname, contacts, description)
            var id = reference.push().key

            // send the data to firebase
            reference.child(id!!).setValue(model)
            et_name.setText("")
            et_email.setText("")
            et_email2.setText("")
            tv_description.setText("")
        } else {
            Toast.makeText(applicationContext, "ALL fields Required", Toast.LENGTH_LONG).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
