package ca.georgiancollege.comp3025_w24_week_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import ca.georgiancollege.comp3025_w24_week_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.clearButton.setOnClickListener{view -> processOperatorButtons(view)}
        binding.percentageButton.setOnClickListener{view -> processOperatorButtons(view)}
        binding.zero.setOnClickListener{view -> processNumberButtons(view)}
    }

    private fun processOperatorButtons(view: View)
    {
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        Log.i("operators", view.tag.toString())
        resultTextView.text = view.tag.toString()
    }

    private fun processNumberButtons(view: View)
    {
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        Log.i("operators", view.tag.toString())
        resultTextView.text = view.tag.toString()
    }
}