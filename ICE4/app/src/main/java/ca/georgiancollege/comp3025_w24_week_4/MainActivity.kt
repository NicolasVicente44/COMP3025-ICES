package ca.georgiancollege.comp3025_w24_week_4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.comp3025_w24_week_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //operator buttons onclick listeners
        binding.clearButton.setOnClickListener{view -> processOperatorButtons(view)}
        binding.percentageButton.setOnClickListener{view -> processOperatorButtons(view)}
        binding.backspaceButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.plusMinusButton.setOnClickListener{view -> processNumberButtons(view)}
        //function/utility buttons
        binding.additionButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.subtractionButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.divisionButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.multiplyButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.equalsButton.setOnClickListener{view -> processNumberButtons(view)}
        //number buttons onclick listeners
        binding.zero.setOnClickListener{view -> processNumberButtons(view)}
        binding.oneButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.twoButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.threeButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.fourButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.fiveButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.sixButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.sevenButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.eightButton.setOnClickListener{view -> processNumberButtons(view)}
        binding.nineButton.setOnClickListener{view -> processNumberButtons(view)}
        //decimal button
        binding.decimalButton.setOnClickListener{view -> processNumberButtons(view)}

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
        Log.i("numbers", view.tag.toString())
        resultTextView.text = view.tag.toString()
    }
}