package com.example.criminalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        wantedBtn.setOnClickListener {
            val intent = Intent(  this, WantedActivity::class.java)
            startActivity(intent)
        }

        missingBtn.setOnClickListener {
            val intent=Intent(this, MissingActivity::class.java)
            startActivity(intent)
        }

    }
}
