package com.example.epolicemanagementsystem.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.epolicemanagementsystem.R
import com.example.epolicemanagementsystem.databinding.ActivityProfileBinding
import com.example.epolicemanagementsystem.login_screen.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    // viewbinding
    private lateinit var binding: ActivityProfileBinding

    // action bar
    private lateinit var actionBar: ActionBar

    // firebaseauth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Profile"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

    }


    private fun checkUser() {
        // check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //use not null , user is login in

            val email = firebaseUser.email
            binding.etEmail.text = email

            val name = firebaseUser.displayName
            binding.etFirstName.text = name

        } else {
            // user is nul not logged in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()


        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
