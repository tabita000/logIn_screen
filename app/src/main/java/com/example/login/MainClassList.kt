package com.example.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainClassList : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode", "UseKtx")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_class_list)

        val swDegreeCert = findViewById<Switch>(R.id.switch1)
        val spnDegree = findViewById<Spinner>(R.id.spinnerMajor)
        val spnCertificate = findViewById<Spinner>(R.id.spinnerCertificate)
        val txtCertificate = findViewById<TextView>(R.id.certificateTextView)
        val txtDegree = findViewById<TextView>(R.id.DegreetextView)
        val btnNext = findViewById<Button>(R.id.button)

        val firstName = findViewById<EditText>(R.id.firstNameEdittxt)
        val lastName = findViewById<EditText>(R.id.LastNameditText)
        val phone = findViewById<EditText>(R.id.editTextPhone)

        val spMonth = findViewById<Spinner>(R.id.spinnerMonth)
        val txtDay = findViewById<EditText>(R.id.DayeditText)
        val txtYear = findViewById<EditText>(R.id.YearEditText)

        firstName.requestFocus()


        swDegreeCert.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                spnDegree.visibility = View.VISIBLE
                txtDegree.visibility = View.VISIBLE
                spnCertificate.visibility = View.GONE
                txtCertificate.visibility = View.GONE
            } else {
                spnDegree.visibility = View.GONE
                txtDegree.visibility = View.GONE
                spnCertificate.visibility = View.VISIBLE
                txtCertificate.visibility = View.VISIBLE
            }
        }

        btnNext.setOnClickListener {
            if (checkData()) {
                var doBirth = ""
                doBirth = spMonth.selectedItem.toString() + "/" + txtDay.text.toString() + "/" + txtYear.text.toString()

                val nextScreen = Intent(this@MainClassList, ChooseClass::class.java)

                nextScreen.putExtra("FirstName", firstName.text.toString())
                nextScreen.putExtra("LastName", lastName.text.toString())
                nextScreen.putExtra("Phone", phone.text.toString())
                nextScreen.putExtra("BirthDate", doBirth)

                if (spnDegree.visibility == View.VISIBLE) {
                    nextScreen.putExtra("isDegreeCert", "Degree")
                    nextScreen.putExtra("degreeCert", spnDegree.selectedItem.toString())
                } else {
                    nextScreen.putExtra("isDegreeCert", "Certificate")
                    nextScreen.putExtra("degreeCert", spnCertificate.selectedItem.toString())
                }

                //Start Activity
                startActivity(nextScreen)

            }




        }
    }
}

private fun MainClassList.checkData(): Boolean {
    val firstName = findViewById<EditText>(R.id.firstNameEdittxt)
    val lastName = findViewById<EditText>(R.id.LastNameditText)
    val phone = findViewById<EditText>(R.id.editTextPhone)
    val txtDay = findViewById<EditText>(R.id.DayeditText)
    val txtYear = findViewById<EditText>(R.id.YearEditText)

    if (firstName.text.toString().isEmpty()) {
        //error
        firstName.error = "Invalid First Name"
        firstName.requestFocus()
        return false
    }

    if (lastName.text.toString().isEmpty()) {
        //error
        lastName.error = "Invalid Last Name"
        lastName.requestFocus()
        return false
    }

    if (phone.text.toString().isEmpty()) {
        //error
        phone.error = "Invalid Phone Number"
        phone.requestFocus()
        return false
    }

    if (txtDay.text.toString().isEmpty()) {
        //error
        txtDay.error = "Invalid Date Selection"
        txtDay.requestFocus()
        return false
    }

    if (txtYear.text.toString().isEmpty()) {
        //error
        txtYear.error = "Invalid Date Selection"
        txtYear.requestFocus()
        return false
    }



    return true
}
