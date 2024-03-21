
package ca.georgiancollege.comp3025_w24_week_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.comp3025_w24_week_10.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FirstAdapter
    private val firestoreDataManager = FirestoreDataManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize RecyclerView
        recyclerView = binding.FirstRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FirstAdapter(emptyList())
        recyclerView.adapter = adapter

        // initialize Firebase
        FirebaseApp.initializeApp(this)

        // get movies from Firestore
        fetchMovies()
    }

    private fun fetchMovies() {
        firestoreDataManager.getMovies { movies ->
            runOnUiThread {
                adapter.updateMovies(movies)
            }
        }
    }
}
