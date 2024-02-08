package ca.georgiancollege.comp3025_w24_week_5

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.comp3025_w24_week_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var resultLabelValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //call onclick listeners function
        initializeOnClickListeners()
    }

    private fun initializeOnClickListeners() {

        //function/utility buttons
        binding.clearButton.setOnClickListener { view -> processExtraButtons(view) }
        binding.percentageButton.setOnClickListener { view -> processExtraButtons(view) }
        binding.backspaceButton.setOnClickListener { view -> processExtraButtons(view) }
        binding.plusMinusButton.setOnClickListener { view -> processExtraButtons(view) }
        //operator buttons onclick listeners
        binding.additionButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.subtractionButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.divisionButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.multiplyButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.equalsButton.setOnClickListener { view -> processOperatorButtons(view) }
        //number buttons onclick listeners
        binding.zero.setOnClickListener { view -> processNumberButtons(view) }
        binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        //decimal button
        binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }


    }

    private fun processOperatorButtons(view: View)
    {
        Log.i("operator buttons", view.tag.toString())
        resultLabelValue += view.tag.toString()
        binding.resultTextView.text = resultLabelValue
    }

    private fun processExtraButtons(view: View)
    {
        when (view.tag.toString()) {
            "clear" -> {
                resultLabelValue = ""
            }
            "backspace" -> {
                if (resultLabelValue.isNotEmpty()) {
                    resultLabelValue = resultLabelValue.substring(0, resultLabelValue.length - 1)
                }
            }
            "+-" -> {
                resultLabelValue = if (resultLabelValue.startsWith("-")) {
                    resultLabelValue.substring(1)
                } else {
                    "-$resultLabelValue"
                }
            }
        }





        Log.i("extra buttons", view.tag.toString())
        binding.resultTextView.text = resultLabelValue
    }



    private fun processNumberButtons(view: View)
    {
        if (view.tag.toString() === ".")
        {
          if(!resultLabelValue.contains("."))
          {
              resultLabelValue += view.tag.toString()
          } else {
              resultLabelValue += ""
          }

        } else {
            resultLabelValue += view.tag.toString()
        }

        Log.i("number buttons", view.tag.toString())
      binding.resultTextView.text = resultLabelValue
    }
}