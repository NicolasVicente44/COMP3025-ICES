package ca.georgiancollege.comp3025_w24_week_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.comp3025_w24_week_8.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the list of movies from the DataManager
        val favouriteMovies = DataManager.instance.deserializeJSON(this)

        // Check if the list is not null
        if (favouriteMovies != null) {
            val firstAdapter = FirstAdapter(favouriteMovies.toTypedArray())
            binding.FirstRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = firstAdapter
            }
        } else {
            Log.i("the list of movies was empty, or there was an error loading them")
        }
    }
}