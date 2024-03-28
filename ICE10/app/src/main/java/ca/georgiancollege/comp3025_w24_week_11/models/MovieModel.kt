package ca.georgiancollege.comp3025_w24_week_11.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieModel(
    val title: String,
    val studio: String
)

