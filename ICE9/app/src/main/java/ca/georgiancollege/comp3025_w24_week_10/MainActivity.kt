package ca.georgiancollege.comp3025_w24_week_10

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.comp3025_w24_week_10.databinding.ActivityMainBinding
import ca.georgiancollege.comp3025_w24_week_10.databinding.AddNewMovieItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FirestoreAdapter
    private val viewModel: MovieViewModel by viewModels()
    private val firestoreDataManager = FirestoreDataManager()
    private lateinit var auth: FirebaseAuth
    private val authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        adapter = FirestoreAdapter(emptyList()) // Initially empty, data will be fetched later
        binding.FirstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.FirstRecyclerView.adapter = adapter

        binding.logoutButton.setOnClickListener { logoutUser() }

        //initialize floating action button for creation of movie
        binding.addMovieFAB.setOnClickListener { showAddMovieDialog() }

    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener)
        fetchMovies()
    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(authListener)
    }


    private fun showAddMovieDialog() {
        val dialogBinding = AddNewMovieItemBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Add New Movie")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                // Handle adding the movie
                val title = dialogBinding.movieTitleEditText.text.toString()
                val studio = dialogBinding.studioTitleEditText.text.toString()

                // Create a new movie object with the entered information
                val newMovie = FirebaseMovie(title, studio)

                // Call the method to add the movie to Firestore
                firestoreDataManager.addMovie(newMovie) { isSuccess ->
                    if (isSuccess) {
                        // Movie added successfully
                        // You can show a message or perform any other action here
                        Log.d(TAG, "Movie added successfully")
                        fetchMovies() // Update the movie list
                    } else {
                        // Movie addition failed
                        // You can show an error message or perform any other action here
                        Log.e(TAG, "Failed to add movie")
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    companion object {
        private const val TAG = "MainActivity" // Use your desired tag for logging
    }


    private fun fetchMovies() {
        firestoreDataManager.getMovies { movies ->
            runOnUiThread {
                adapter.updateMovies(movies) // Update the adapter with the new list of movies
            }
        }
    }

    private fun logoutUser() {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("auth_token")
            apply()
        }
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}


