package ca.georgiancollege.comp3025_w24_week_6

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.js.ExperimentalJsFileName

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //get text from the resource
        println(getTextFromAsset(this, "contacts.json"))
    }



    private fun  getTextFromResource(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText() }
    }


    private fun getTextFromAsset(context: Context, fileName: String): String {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }
}