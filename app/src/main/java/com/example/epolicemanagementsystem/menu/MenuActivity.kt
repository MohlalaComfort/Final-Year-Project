package com.example.epolicemanagementsystem.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.epolicemanagementsystem.MainActivity
import com.example.epolicemanagementsystem.R
import com.example.epolicemanagementsystem.login_screen.LoginActivity
import com.example.epolicemanagementsystem.report_screen.ReportMainActivity
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.hide()

        val profile = findViewById<CardView>(R.id.profile_card)
        val message = findViewById<CardView>(R.id.messages)
        val report = findViewById<CardView>(R.id.report)
        val manageCall = findViewById<CardView>(R.id.call)
        val help = findViewById<CardView>(R.id.help)
        val logout = findViewById<CardView>(R.id.logout)


        // Go to profile activity
        profile.setOnClickListener{

            startActivity(Intent(this, ProfileActivity::class.java))
        }
        // Go to message activity
        message.setOnClickListener{

            startActivity(Intent(this, MainActivity::class.java))
        }


        // Access the report Activity
        report.setOnClickListener{

            startActivity(Intent(this, ReportMainActivity::class.java))
        }

        // Start call intent Activity
        manageCall.setOnClickListener{

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10111")
            startActivity(intent)
        }

        //Go to Help Activity
        help.setOnClickListener{

            startActivity(Intent(this, HelpActivity::class.java))
        }


        // Logout
        logout.setOnClickListener{

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MenuActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}