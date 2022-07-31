package com.example.simplechatapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.get


import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class Login : AppCompatActivity() {

    private lateinit var edtEmail : TextInputEditText
    private lateinit var edtPassword : TextInputEditText
    private lateinit var btnLogin : Button
    private lateinit var btnSignup : Button
    private lateinit var lgnLayout:RelativeLayout
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth = FirebaseAuth.getInstance() //Firebase Authentication



        lgnLayout=findViewById(R.id.login_layout)
        edtEmail = findViewById(R.id.username_text)
        edtPassword = findViewById(R.id.user_password)
        btnLogin = findViewById(R.id.edt_login_button)
        btnSignup = findViewById(R.id.edt_register_text_button)

        animationDrawable= lgnLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()





        btnSignup.setOnClickListener {

            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {

            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            if(email.length<15 || password.length<6){
                Toast.makeText(baseContext, "Insert Valid Email or Password.",Toast.LENGTH_SHORT).show()


            }
            else{
                login(email,password)

            }

        }

        }

        private fun login(email:String,password:String){

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val intent = Intent(this@Login,MainActivity::class.java)
                        startActivity(intent)

                    } else {                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()

                    }
                }


            ///Create Login




        }



}







