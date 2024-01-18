package ca.georgiancollege.comp3025_w2024_week_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        greetingTextView.text = "Hello Nick"

        Log.i("onCreate", "This is on create")
        println("hello world")
    }
}