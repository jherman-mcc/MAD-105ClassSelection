package com.example.classselection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.classselection.R
import com.example.classselection.R.*

enum class LoginSuccess
constructor(val intValue: Int){
    login(1),
    password(2),
    success(0)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val txtLogin = findViewById<TextView>(id.idLoginText)
        val txtPassword = findViewById<TextView>(id.idLoginPassword)
        val btnLogin = findViewById<Button>(id.btnLogin)

        btnLogin.setOnClickListener{
            when (CheckLogin(txtLogin.text.toString(),txtPassword.text.toString())) {
                LoginSuccess.login -> {
                    // Toast.makeText(applicationContext,getString(R.string.errMessageLogin),Toast.LENGTH_LONG).show()
                    var toast1 = Toast.makeText(applicationContext,getString(string.errMessageLogin),Toast.LENGTH_LONG)
                    toast1.setGravity(Gravity.CENTER_VERTICAL,0,-100)

                    toast1.show()


                    txtLogin.requestFocus()
                }
                LoginSuccess.password -> {
                    // Toast.makeText(applicationContext,getString(R.string.errMessagePassword),Toast.LENGTH_LONG).show()
                    var toast2 = Toast.makeText(applicationContext,getString(string.errMessagePassword),Toast.LENGTH_LONG)
                    toast2.setGravity(Gravity.CENTER_VERTICAL,0,-100)

                    toast2.show()

                    txtPassword.requestFocus()
                }
                else ->
                {
//                Use this Toast code if you want to see if the login is successful
                    Toast.makeText(applicationContext,"Success", Toast.LENGTH_LONG).show()
//                Otherwise go ahead to the next screen using Intent. I am using
//                two lines for intent because one line didn't work.
                    val nextScreen = Intent(this,MainClassList::class.java)
                    startActivity(nextScreen)
                }
            }
        }
    }
    fun CheckLogin(txtLogin: String, txtPassword: String): LoginSuccess {
        val holdLogin = "Jeff"
        val holdPass = "Password"

        if (holdLogin != txtLogin) {
            return LoginSuccess.login
        }
        return if (holdPass != txtPassword) {
            return LoginSuccess.password
        } else LoginSuccess.success
    }
}