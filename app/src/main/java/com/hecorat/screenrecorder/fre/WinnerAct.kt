package com.hecorat.screenrecorder.fre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_winner.*

class WinnerAct : AppCompatActivity() {
    var pressedTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)
        var btn = findViewById<Button>(R.id.button_retry)
        btn.setOnClickListener {
            startActivity(Intent(this, GameAct::class.java))
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()

        } else {
            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
    }
