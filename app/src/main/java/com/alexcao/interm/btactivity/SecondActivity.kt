package com.alexcao.interm.btactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : AppCompatActivity() {

    lateinit var urlEditing: EditText
    lateinit var getButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        urlEditing = findViewById(R.id.url_et)
        getButton = findViewById(R.id.get_btn)

        getButton.setOnClickListener {
            val url = urlEditing.text.toString()
            val intent = intent
            intent.putExtra("url", url)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}