package com.example.epolicemanagementsystem.register_screen

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import com.example.epolicemanagementsystem.R
import com.example.epolicemanagementsystem.chatScreen.User
import com.example.epolicemanagementsystem.databinding.ActivitySignUpBinding
import com.example.epolicemanagementsystem.login_screen.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : AppCompatActivity() {

    //View Binding
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mDbRef: DatabaseReference
    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog
    //Firebase auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var name =""
    private var email =""
    private var password =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Creating new Account")
        progressDialog.setMessage("Please wait")
        progressDialog.setCanceledOnTouchOutside(true)

        //Get reference to the view
        val signInRbn = findViewById<RadioButton>(R.id.login_rbn)

        //Go back to Login page
        signInRbn.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // initialise firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        // handle click, begin signup
        binding.signUp.setOnClickListener {
            //validate data
            validateData(name,email,password)
        }


    }

    private fun validateData(name: String, email: String, password: String){
        this.email =binding.email.text.toString().trim()
        this.password =binding.passwordEt.text.toString().trim()
        this.name=binding.name.text.toString()

        //Validate
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //Invalid email format
        } else if (TextUtils.isEmpty(password)) {
            //Password not entered
            binding.passwordEt.error = "Please enter password"
        } else if (password.length < 6) {
            binding.passwordEt.error = "Password must at least be 6 characters long"
        } else {
            //Data is valid, continue signup
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //Show progress
        progressDialog.show()
        //Create an account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //User registration success
                progressDialog.dismiss()

                addUserToDatabase(name, email, firebaseAuth.currentUser?.uid!!)
                // get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()

                //Open Main Menu
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                //Sign up failed
                Toast.makeText(this, "Sign Up failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }

}