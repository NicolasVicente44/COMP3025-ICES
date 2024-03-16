package ca.georgiancollege.comp3025_w24_week_9


import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class FirestoreDataManager {
    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection("movies")

    fun getMovies(onComplete: (List<FirebaseMovie>) -> Unit) {
        collectionRef.get()
            .addOnSuccessListener { result ->
                val movies = result.mapNotNull { it.toObject<FirebaseMovie>() }
                onComplete(movies)
            }
            .addOnFailureListener {
                onComplete(emptyList())
            }
    }

    fun addMovie(movie: FirebaseMovie, onComplete: (Boolean) -> Unit) {
        collectionRef.add(movie)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    //to do, get movie by id

    fun getMovieById(movieId: String, onComplete: (FirebaseMovie?) -> Unit) {
        collectionRef.document(movieId).get()
            .addOnSuccessListener { document ->
                val movie = document.toObject<FirebaseMovie>()
                onComplete(movie)
            }
            .addOnFailureListener {
                onComplete(null)
            }
    }

    // update movie

    fun updateMovie(movieId: String, movie: FirebaseMovie, onComplete: (Boolean) -> Unit) {
        collectionRef.document(movieId).set(movie)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    //to do, delete movie

    fun deleteMovie(movieId: String, onComplete: (Boolean) -> Unit) {
        collectionRef.document(movieId).delete()
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }


}