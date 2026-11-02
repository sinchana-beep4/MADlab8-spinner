package com.example.madlab8_spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val country = listOf<String>("India", "United States of America", "Cuba", "Spain", "Nepal", "Bhutan", "Sri Lanka", "Russia", "Canada", "Ghana", "Sweden", "Finland", "Germany")
        val spin = findViewById<Spinner>(R.id.spin)
        val display = findViewById<TextView>(R.id.display)
        val submit = findViewById<Button>(R.id.submit)
        val aa = ArrayAdapter(this,android.R.layout.simple_spinner_item ,country)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = aa
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null){
                    display.text = parent.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        submit.setOnClickListener {
            val build = AlertDialog.Builder(this)
            build.setTitle("Confirm Selection")
            build.setMessage("Ensure you have selected the correct country")
            build.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext, "Recorded!", Toast.LENGTH_LONG).show()
            }
            build.setNeutralButton("Cancel"){dialogInterface, which ->
                finish()
            }
            build.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(applicationContext, "Please select a country", Toast.LENGTH_LONG).show()
            }

            val alert : AlertDialog = build.create()
            alert.setCancelable(true)
            alert.show()
        }
    }
}