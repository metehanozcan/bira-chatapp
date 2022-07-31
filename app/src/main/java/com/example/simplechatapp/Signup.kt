package com.example.simplechatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Signup : AppCompatActivity() {
    private lateinit var edtEmail : TextInputEditText
    private lateinit var edtPassword : TextInputEditText
    private lateinit var rgsUsrName : TextInputEditText
    private lateinit var btnSignup : Button
//    private lateinit var rgsLayout: RelativeLayout
//    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance() //Firebase Authentication



//        rgsLayout=findViewById(R.id.rgs_layout)
        edtEmail = findViewById(R.id.username_text)
        edtPassword = findViewById(R.id.user_password)
        rgsUsrName=findViewById(R.id.real_user_name_text)
        btnSignup = findViewById(R.id.register_button)




//        animationDrawable= rgsLayout.background as AnimationDrawable
//        animationDrawable.setEnterFadeDuration(2500)
//        animationDrawable.setExitFadeDuration(5000)
//        animationDrawable.start()


        btnSignup.setOnClickListener {

            val email=edtEmail.text.toString()
            val password=edtPassword.text.toString()
            val name= rgsUsrName.text.toString()

            signup(name,email,password)

        }
    }
    private fun addUsertoDatabase(name: String,email: String,uid:String) {
        mDbRef=FirebaseDatabase.getInstance().reference
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }


    /// Create user SIGN UP
    private fun signup(name: String,email:String,password:String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //GO TO HOME
//                    updateProfile(name)//Declaring the name :)
                    addUsertoDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent= Intent(this@Signup,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Signup, "Authentication failed." , Toast.LENGTH_SHORT).show()
                }
            }

    }

}