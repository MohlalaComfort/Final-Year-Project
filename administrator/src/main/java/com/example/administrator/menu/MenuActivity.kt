package com.example.administrator.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.administrator.MainActivity
import com.example.administrator.R
import com.example.administrator.generateReport.Getdata
import com.example.administrator.generateReport.ReportMainActivity
import com.example.administrator.loginscreen.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.hide()

        val profile = findViewById<CardView>(R.id.profile_card)
        val message = findViewById<CardView>(R.id.messages)
        val settings = findViewById<CardView>(R.id.generate_report)
        val manage = findViewById<CardView>(R.id.manageFiles)
        val info = findViewById<CardView>(R.id.info)
        val logout = findViewById<CardView>(R.id.logout)

        // Go to profile activity
        profile.setOnClickListener{

            startActivity(Intent(this, ProfileActivity::class.java))
        }
        // Go to message activity
        message.setOnClickListener{

            startActivity(Intent(this, MainActivity::class.java))
        }


        // Access Generate Report Activity
        settings.setOnClickListener{

            startActivity(Intent(applicationContext, Getdata::class.java))
        }

        // Access the manage file Activity
        manage.setOnClickListener{

            //startActivity(Intent(this, SurveyResultsActivity::class.java))
        }

        //Go to Info Activity
        info.setOnClickListener{

            startActivity(Intent(this, InformationActivity::class.java))
        }

        // Logout
        logout.setOnClickListener{

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MenuActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}