package ca.georgiancollege.comp3025_w24_week_10

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirestoreDataManager {
    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection("movies")

    fun getMovies(onComplete: (List<FirebaseMovie>) -> Unit) {
             collectionRef.orderBy("title").get()

            .addOnSuccessListener { result: QuerySnapshot ->
                val movies = result.toObjects(FirebaseMovie::class.java)
                onComplete(movies)
            }
            .addOnFailureListener { exception ->
                // Handle errors
                onComplete(emptyList())
            }
    }

    fun addMovie(movie: FirebaseMovie, onComplete: (Boolean) -> Unit) {
        collectionRef.add(movie)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun updateMovie(movieId: String, updatedMovie: FirebaseMovie, onComplete: (Boolean) -> Unit) {
        collectionRef.document(movieId)
            .set(updatedMovie)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun deleteMovie(movieId: String, onComplete: (Boolean) -> Unit) {
        collectionRef.document(movieId)
            .delete()
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }
}
