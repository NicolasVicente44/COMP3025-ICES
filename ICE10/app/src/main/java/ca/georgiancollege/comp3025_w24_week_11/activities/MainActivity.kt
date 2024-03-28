package ca.georgiancollege.comp3025_w24_week_11.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.comp3025_w24_week_11.databinding.ActivityMainBinding

import androidx.activity.viewModels
import ca.georgiancollege.comp3025_w24_week_11.models.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FirestoreAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = FirestoreAdapter(emptyList()) // Initially empty, data will be fetched later
        binding.FirstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.FirstRecyclerView.adapter = adapter





    }



}

