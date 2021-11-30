package com.example.administrator.registerscreen

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.administrator.chatScreen.User
import com.example.administrator.R
import com.example.administrator.databinding.ActivitySignUpBinding
import com.example.administrator.generateReport.Getdata
import com.example.administrator.loginscreen.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding : ActivitySignUpBinding
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

        //Go back to Login page
        binding.loginRbn.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        //Initialise firebase auth
        firebaseAuth= FirebaseAuth.getInstance()

        val sign_up =findViewById<Button>(R.id.sign_up)
        //Handle click, begin signup
        sign_up.setOnClickListener {
            //Validate data
            validateData(name,email,password)
        }


    }

    private fun validateData(name: String, email: String, password: String) {
        this.email =binding.email.text.toString().trim()
        this.password =binding.passwordEt.text.toString().trim()
        this.name=binding.name.text.toString()

        // validate
        if (!Patterns.EMAIL_ADDRESS.matcher(this.email).matches()){
            //invalid email format
        }
        else if (TextUtils.isEmpty(this.password)){
            //Password not entered
            binding.passwordEt.error="Please enter password"
        }
        else if (this.password.length<6){
            binding.passwordEt.error="Password must at least be 6 characters long"
        }
        else{
            //Data is valid, continue signup
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //Show progress
        progressDialog.show()
        //Create account

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //User registration success
                progressDialog.dismiss()

                addUserToDatabase(name, email, firebaseAuth.currentUser?.uid!!)
                //Get current user
                val firebaseUser=firebaseAuth.currentUser
                val email=firebaseUser!!.email
                val name=firebaseUser!!.displayName
                Toast.makeText(this,"Account created with email $email",Toast.LENGTH_SHORT).show()


                //Open Main Menu
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            . addOnFailureListener { e ->
                //Sign up failed
                Toast.makeText(this,"Sign Up failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }
    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }


}