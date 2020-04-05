package com.example.criminalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pinpointed.*

class PinpointedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pinpointed)

        returnToDashboardBtn.setOnClickListener {
            val intent= Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }
}
