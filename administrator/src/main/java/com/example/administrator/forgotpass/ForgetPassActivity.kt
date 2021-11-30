package com.example.administrator.forgotpass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.administrator.R
import com.example.administrator.loginscreen.LoginActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ForgetPassActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        val send_btn = findViewById<Button>(R.id.email_btn)
        val email_txt = findViewById<EditText>(R.id.email_rqst)

        //Go to login page after requesting
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