package ca.georgiancollege.comp3025_w24_week_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.comp3025_w24_week_9.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the list of movies from the data manager clss and read json
        val favouriteMovies = DataManager.instance.deserializeJSON(this)

        // validate that the list contains items
        if (favouriteMovies != null) {
            val firstAdapter = FirstAdapter(favouriteMovies)
            binding.FirstRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = firstAdapter
            }
        } else {
            Log.v("MyTag", "the list of movies was empty, or there was an error loading them")
        }
    }
}