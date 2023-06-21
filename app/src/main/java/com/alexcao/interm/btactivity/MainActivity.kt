package com.alexcao.interm.btactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    lateinit var accessButton: Button
    lateinit var typeButton: Button
    lateinit var urlView: TextView

    var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when(result.resultCode) {
            RESULT_OK -> {
                urlView.text = result.data?.getStringExtra("url")
            }
            RESULT_CANCELED -> {
                Log.d(TAG, "startSecondActivity: RESULT_CANCELED")
            }
        }
    }

    private fun startSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        result.launch(intent)
    }

    private fun accessUrl() {
        val url = urlView.text.toString()
        if(isValidUrl(url)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(url)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "accessUrl: Invalid URL")
        }
    }

    private fun isValidUrl(url: String): Boolean {
        return android.util.Patterns.WEB_URL.matcher(url).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accessButton = findViewById(R.id.go_btn)
        typeButton = findViewById(R.id.type_btn)
        urlView = findViewById(R.id.url_view)

        typeButton.setOnClickListener {
            startSecondActivity()
        }

        accessButton.setOnClickListener {
            accessUrl()
        }
    }
}