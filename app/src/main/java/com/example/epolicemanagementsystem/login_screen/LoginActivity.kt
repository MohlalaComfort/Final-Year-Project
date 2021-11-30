package com.example.epolicemanagementsystem.login_screen

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.example.epolicemanagementsystem.MainActivity
import com.example.epolicemanagementsystem.R
import com.example.epolicemanagementsystem.databinding.ActivityLoginBinding
import com.example.epolicemanagementsystem.forgot_pass_screen.ForgotPassActivity
import com.example.epolicemanagementsystem.menu.MenuActivity
import com.example.epolicemanagementsystem.register_screen.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    //View Binding
    private lateinit var binding: ActivityLoginBinding
    //Progress bar
    private lateinit var progressDialog: ProgressDialog
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Log In")
        progressDialog.setMessage("Please wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        //Initialise firebaseauth
        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

        //Handle click, open register activity
        binding.register.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java ))
        }

        //handle click, open forgot pass activity
        binding.passTxt.setOnClickListener {
            startActivity(Intent(this,ForgotPassActivity::class.java ))
        }

        //Handle click, begin login
        binding.signIn.setOnClickListener {
            //Before logging in, validate data
            validateData()
        }

    }

    private fun validateData() {
        //Get data
        email = binding.email.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //Validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //Invalid email format error handling
            binding.email.setError("Invalid email format")
        }
        else if (TextUtils.isEmpty(password)){
            //No password entered error handling
            binding.passwordEt.error="Please enter password"
        }
        else {
            // data is validated begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //Show progress
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //Login success
                progressDialog.dismiss()
                //Get current user information
                val firebaseUser=firebaseAuth.currentUser
                val email =firebaseUser!!.email
                Toast.makeText(this,"Logged In as $email",Toast.LENGTH_SHORT).show()

                //Opening menu activity
                startActivity(Intent(this,MenuActivity::class.java))
                finish()

            }
            .addOnFailureListener{ ex ->
                //Login failed
                Toast.makeText(this,"Login Failed due to ${ex.message}",Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
    }


    private fun checkUser() {
        //Get current user
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser != null){
            //User is already logged in
            startActivity(Intent(this,MenuActivity::class.java))
            finish()
        }
    }

}