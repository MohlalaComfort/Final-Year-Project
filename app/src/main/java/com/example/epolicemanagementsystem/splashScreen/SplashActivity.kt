package com.example.epolicemanagementsystem.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.epolicemanagementsystem.R
import com.example.epolicemanagementsystem.login_screen.LoginActivity

class SplashActivity : AppCompatActivity() {
    // The loading time of the splash screen
    private val splashTimeOut: Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, splashTimeOut)
    }
}