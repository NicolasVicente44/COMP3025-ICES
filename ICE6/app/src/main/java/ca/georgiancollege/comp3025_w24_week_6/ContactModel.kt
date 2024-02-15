package ca.georgiancollege.comp3025_w24_week_6

import com.squareup.moshi.JsonClass


/**
 * Creates an instance of the contact model data class
 * @param FullName [String]
 * @param ContactNumber [String]
 * @param EmailAddress [String]
 */

@JsonClass(generateAdapter = true)
data class ContactModel(
    val FullName: String,
    val ContactNumber: String,
    val EmailAddress: String
) {


    /**
     * Overridden toString method
     */
    override fun toString(): String {
        return """Full Name: $FullName, Contact Number: $ContactNumber, Email Address: $EmailAddress """
    }

}
