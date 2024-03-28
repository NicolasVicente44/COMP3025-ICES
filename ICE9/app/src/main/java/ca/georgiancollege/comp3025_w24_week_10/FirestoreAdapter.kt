package ca.georgiancollege.comp3025_w24_week_10

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.comp3025_w24_week_10.databinding.TextRowItemBinding

private val firestoreDataManager = FirestoreDataManager()
class FirestoreAdapter(private var dataSet: List<FirebaseMovie>) :
    RecyclerView.Adapter<FirestoreAdapter.ViewHolder>() {

    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        holder.binding.title.text = movie.title
        holder.binding.studio.text = movie.studio
    }

    override fun getItemCount() = dataSet.size

    fun updateMovies(movies: List<FirebaseMovie>) {
        dataSet = movies
        notifyDataSetChanged()
    }

    fun getMovieAtPosition(position: Int): FirebaseMovie {
        return dataSet[position]
    }

    fun removeItem(position: Int) {
        dataSet = dataSet.toMutableList().also { it.removeAt(position) }
        notifyItemRemoved(position)
    }



    fun deleteMovie(movieId: String) {
        firestoreDataManager.deleteMovie(movieId) { isSuccess ->
            if (isSuccess) {
                // Find the position of the movie with the given movieId
                val position = dataSet.indexOfFirst { "${it.title}_${it.studio}" == movieId }
                if (position != -1) {
                    removeItem(position)
                }
            } else {
                Log.e("Error deleting movie", "Error deleting movie from Firestore")
            }
        }
    }





}
