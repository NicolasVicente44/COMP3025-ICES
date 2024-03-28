package ca.georgiancollege.comp3025_w24_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.comp3025_w24_week_10.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import ca.georgiancollege.comp3025_w24_week_10.databinding.AddNewMovieItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        // Setup swipe to delete
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // not used
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movieId = adapter.getItemId(position)
                // Call deleteMovie method from FirestoreDataManager passing movieId
                firestoreDataManager.deleteMovie(movieId.toString()) { isSuccess ->
                    if (isSuccess) {
                        viewModel.getAllMovies() // Refresh movies
                    } else {
                        // Handle deletion failure
                    }
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.FirstRecyclerView)

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

                        Log.d("Successful movie add", "Movie added successfully")
                        fetchMovies() // Update the movies displayed to include the new movie added
                    } else {

                        Log.e("Failed movie add", "Failed to add movie")
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
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

