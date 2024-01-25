package ca.georgiancollege.comp3025_w24_week_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val clearButton = findViewById<Button>(R.id.clearButton)
        val percentageButton = findViewById<Button>(R.id.percentageButton)

        clearButton.setOnClickListener{view -> processOperatorButtons(view)}
        percentageButton.setOnClickListener{view -> processOperatorButtons(view)}

    }

    private fun processOperatorButtons(view: View)
    {
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        Log.i("operators", view.tag.toString())
        resultTextView.text = view.tag.toString()
    }

}