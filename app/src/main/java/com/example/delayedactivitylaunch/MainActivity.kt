package com.example.delayedactivitylaunch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button).setOnClickListener {
            val progressBar = findViewById<View>(R.id.progress_bar)
            progressBar.visibility = View.VISIBLE
            it.postDelayed({
                progressBar.visibility = View.GONE
                startActivity(Intent(this, MainActivity2::class.java))
            }, 5000)
        }
    }
}
