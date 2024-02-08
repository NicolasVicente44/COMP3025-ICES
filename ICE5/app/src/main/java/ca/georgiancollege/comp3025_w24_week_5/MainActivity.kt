package ca.georgiancollege.comp3025_w24_week_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.comp3025_w24_week_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?)
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        calculator = Calculator(binding)
    }


}