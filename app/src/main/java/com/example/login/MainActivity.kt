package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    enum class LoginSuccess
    constructor(val intValue: Int) {
        login(1),
        password(2),
        success(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtLogin = findViewById<TextView>(R.id.idLoginText)
        val txtPassword = findViewById<TextView>(R.id.idPasswordText)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            when (CheckLogin(txtLogin.text.toString(), txtPassword.text.toString())) {

                LoginSuccess.login -> {
                    Toast.makeText(applicationContext, getString(R.string.error_msg_login), Toast.LENGTH_LONG).show()
                    txtLogin.requestFocus()
                }

                LoginSuccess.password -> {
                    Toast.makeText(applicationContext, getString(R.string.error_password), Toast.LENGTH_LONG).show()
                    txtPassword.requestFocus()
                }

                else -> {
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainClassList::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun CheckLogin(txtLogin: String, txtPassword: String): LoginSuccess {
        val holdLogin = "Tabita"
        val holdPass = "password"

        if (holdLogin != txtLogin) {
            return LoginSuccess.login
        }
        return if (holdPass != txtPassword) {
            return LoginSuccess.password
        } else LoginSuccess.success
    }
}
