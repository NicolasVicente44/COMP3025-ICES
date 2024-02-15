package ca.georgiancollege.comp3025_w24_week_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var contact = ContactModel("Nicolas Vicente", "7052412541", "nicolas@gmail.com")

        println(contact)
    }
}