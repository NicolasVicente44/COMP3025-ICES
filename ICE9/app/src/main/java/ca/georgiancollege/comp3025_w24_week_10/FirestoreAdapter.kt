package ca.georgiancollege.comp3025_w24_week_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.comp3025_w24_week_10.databinding.TextRowItemBinding

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

}
