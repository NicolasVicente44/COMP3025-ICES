package ca.georgiancollege.comp3025_w24_week_6

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.js.ExperimentalJsFileName

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        for (contact in deserializeJSON()!!) {
            println(contact)
        }
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


    private fun deserializeJSON(): List<ContactModel>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java)
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)

        val contactListRawString = getTextFromResource(this, R.raw.contacts)
        val contactList: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList
    }


}