package ca.georgiancollege.comp3025_w24_week_8

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


/**
 * the data manager class is a singleton
 */
class DataManager private constructor(){

    fun  getTextFromResource(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText() }
    }


    fun getTextFromAsset(context: Context, fileName: String): String {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }


    fun deserializeJSON(context: Context): List<MovieModel>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, MovieModel::class.java)
        val adapter: JsonAdapter<List<MovieModel>> = moshi.adapter(listType)

        val movieListRawString = getTextFromResource(context, R.raw.movies)
        val movieList: List<MovieModel>? = adapter.fromJson(movieListRawString)
        return movieList
    }

    companion object
    {
        val instance: DataManager by lazy { DataManager() }
    }

}