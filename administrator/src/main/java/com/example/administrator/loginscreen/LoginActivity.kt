package com.example.administrator.loginscreen

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.databinding.ActivityLoginBinding
import com.example.administrator.forgotpass.ForgetPassActivity
import com.example.administrator.menu.MenuActivity
import com.example.administrator.registerscreen.SignUpActivity
import com.google.firebase.auth.FirebaseAuth


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


        //Initialise Firebaseauth
        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

        //Handle click, open register activity
        binding.register.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java ))
        }

        //Handle click, open forgot password activity
        binding.passTxt.setOnClickListener {
            startActivity(Intent(this,ForgetPassActivity::class.java ))
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
            //Data is validated begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //Show progress
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //Login successful
                progressDialog.dismiss()
                //Get current user information
                val firebaseUser=firebaseAuth.currentUser
                val email =firebaseUser!!.email
                Toast.makeText(this,"Logged In as $email",Toast.LENGTH_SHORT).show()

                //Opening menu activity
                startActivity(Intent(this,MenuActivity::class.java))
                finish()

            }
            .addOnFailureListener{ e ->
                //Login failed
                Toast.makeText(this,"Login Failed due to ${e.message}",Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
    }


    private fun checkUser() {
        // Get current user
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser != null){
            //User is already logged in
            startActivity(Intent(this,MenuActivity::class.java))
            finish()

        }
    }

}