package com.example.classselection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainClassList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_class_list)

        val swDegreeCert = findViewById(R.id.swDegreeCert) as Switch
        val spnDegree = findViewById(R.id.spnDegree) as Spinner
        val spnCertificate = findViewById(R.id.spnCertificate) as Spinner
        val txtCertificate = findViewById(R.id.lblCertificate) as TextView
        val txtDegree = findViewById(R.id.lblDegree) as TextView
        val btnNext = findViewById(R.id.btnNext) as Button

        val firstName = findViewById(R.id.txtFirstName) as EditText
        val lastName = findViewById(R.id.txtLastName) as EditText
        val phone = findViewById(R.id.txtPhone) as EditText

        val spMonth = findViewById(R.id.spnMonth) as Spinner
        val txtDay = findViewById(R.id.txtDay) as EditText
        val txtYear = findViewById(R.id.txtYear) as EditText

        firstName.requestFocus()

        swDegreeCert.setOnCheckedChangeListener {buttonView, isChecked ->
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
                doBirth =
                    spMonth.selectedItem.toString() + "/" + txtDay.text.toString() + "/" + txtYear.text.toString()

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

//  Start Activity
                startActivity(nextScreen)


            }

        }
    }

    private fun checkData(): Boolean  {
        val firstName = findViewById(R.id.txtFirstName) as EditText
        val lastName = findViewById(R.id.txtLastName) as EditText
        val phone = findViewById(R.id.txtPhone) as EditText

        val txtDay = findViewById(R.id.txtDay) as EditText
        val txtYear = findViewById(R.id.txtYear) as EditText

        if (firstName.text.toString().isEmpty()) {
            // error
            firstName.error = "Invalid First Name"
            firstName.requestFocus()
            return false
        }
        if (lastName.text.toString().isEmpty()) {
            // error
            lastName.error = "Invalid Last Name"
            lastName.requestFocus()
            return false
        }

        if (phone.text.toString().isEmpty()) {
            // error
            phone.error = "Invalid Phone Number"
            phone.requestFocus()
            return false
        }
        if (txtDay.text.toString().isEmpty()) {
            // error
            txtDay.error = "Invalid Day of the month"
            txtDay.requestFocus()
            return false
        }

        if (txtYear.text.toString().isEmpty()) {
            // error
            txtYear.error = "Invalid Year"
            txtYear.requestFocus()
            return false
        }

        return true
    }

}