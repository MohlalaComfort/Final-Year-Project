package com.example.epolicemanagementsystem.forgot_pass_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.epolicemanagementsystem.R
import com.example.epolicemanagementsystem.login_screen.LoginActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class ForgotPassActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        supportActionBar?.hide()

        val send_btn = findViewById<Button>(R.id.email_btn)
        val email_txt = findViewById<EditText>(R.id.email_rqst)
        mAuth = FirebaseAuth.getInstance()

        send_btn.setOnClickListener {
            val email = email_txt.text.toString().trim()

            if(email.isEmpty()){
                email_txt.error = "Enter Email"
                return@setOnClickListener
            }

            forgetPassword(email)

            Snackbar.make(
                findViewById(android.R.id.content),
                "Email sent",
                Snackbar.LENGTH_LONG
            ).setAction("Dismiss") {
                // No call action functions
            }.show()
        }

    }

    private fun forgetPassword(email: String) {

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task: Task<Void> ->
            if(task.isComplete){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                }
            }
    }
}