package ca.georgiancollege.comp3025_w24_week_5
import android.util.Log
import android.view.View
import ca.georgiancollege.comp3025_w24_week_5.databinding.ActivityMainBinding

class Calculator(binding: ActivityMainBinding)
{
    private var m_resultLabelValue: String = ""
    private var m_binding: ActivityMainBinding
    init 
    {
        this.m_binding = binding
        this.m_resultLabelValue = ""
        initializeOnClickListeners()
    }

    private fun initializeOnClickListeners() {

        //function/utility buttons
        this.m_binding.clearButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.backspaceButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.plusMinusButton.setOnClickListener { view -> processExtraButtons(view) }
        //operator buttons onclick listeners
        this.m_binding.additionButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.subtractionButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.divisionButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.multiplyButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.equalsButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.percentageButton.setOnClickListener { view -> processOperatorButtons(view) }
        //number buttons onclick listeners
        this.m_binding.zero.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        //decimal button
        this.m_binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }


    }

    private fun processOperatorButtons(view: View)
    {
        Log.i("operator buttons", view.tag.toString())
        this.m_resultLabelValue += view.tag.toString()
        this.m_binding.resultTextView.text = this.m_resultLabelValue
    }

    private fun processExtraButtons(view: View)
    {
        when (view.tag.toString()) {
            "clear" -> {
                this.m_resultLabelValue = ""
            }
            "backspace" -> {
                if (this.m_resultLabelValue.isNotEmpty()) {
                    this.m_resultLabelValue = this.m_resultLabelValue.substring(0, this.m_resultLabelValue.length - 1)
                }
            }
            "+-" -> {
                this.m_resultLabelValue = if (this.m_resultLabelValue.startsWith("-")) {
                    this.m_resultLabelValue.substring(1)
                } else {
                    "-$this.m_resultLabelValue"
                }
            }
        }

        Log.i("extra buttons", view.tag.toString())
        this.m_binding.resultTextView.text = this.m_resultLabelValue
    }



    private fun processNumberButtons(view: View)
    {
        if (view.tag.toString() == ".")
        {
            if(!this.m_resultLabelValue.contains("."))
            {
                this.m_resultLabelValue += view.tag.toString()
            } else {
                this.m_resultLabelValue += ""
            }

        } else {
            this.m_resultLabelValue += view.tag.toString()
        }

        Log.i("number buttons", view.tag.toString())
        this.m_binding.resultTextView.text = this.m_resultLabelValue
    }

}