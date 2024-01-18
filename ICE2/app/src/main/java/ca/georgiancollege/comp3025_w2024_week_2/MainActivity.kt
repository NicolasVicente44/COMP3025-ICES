package ca.georgiancollege.comp3025_w2024_week_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)

        Log.i("onCreate", "This is logged on create")
        println("hello world")


        val clickMeButton = findViewById<Button>(R.id.clickMeButton)
        clickMeButton.text = "Click me!"

        val clickMeButton2 = findViewById<Button>(R.id.clickMeButton2)
        clickMeButton2.text = "Second Click me Button!"

        val buttonClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.clickMeButton -> {
                    greetingTextView.text =
                        if (greetingTextView.text == "Hello World!") "Goodbye, world!" else "Hello World!"
                    clickMeButton.text =
                        if (clickMeButton.text == "Click me!") "Un-click me!" else "Click me!"


                }

                R.id.clickMeButton2 -> {
                    greetingTextView.text =
                        if (greetingTextView.text == "Hello World!" || greetingTextView.text == "Goodbye, world!") "You found the second button!" else "Hello World!"
                    clickMeButton2.text =
                        if (clickMeButton2.text == "Second Click me Button!") "Un-click me!" else "Second Click me Button!"
                    greetingTextView.gravity = android.view.Gravity.CENTER

                }
            }
        }

        clickMeButton.setOnClickListener(buttonClickListener)
        clickMeButton2.setOnClickListener(buttonClickListener)
    }
}