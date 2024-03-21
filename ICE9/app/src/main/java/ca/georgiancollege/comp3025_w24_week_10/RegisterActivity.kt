package ca.georgiancollege.comp3025_w24_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ca.georgiancollege.comp3025_w24_week_10.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.CancelButton.setOnClickListener {
            finish()
        }

        binding.RegisterButton.setOnClickListener {
            val email = binding.EmailEditText.text.toString()
            val password = binding.PasswordText.text.toString()
            registerUser(email, password)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success, update UI with the signed-in user's information
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    println("User Registration Successful")
                    finish() // Finish the activity and go back to the login screen
                } else {
                    // If registration fails, display a message to the user.
                    val exception = task.exception
                    if (exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, "Email address is already in use", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Registration failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                    println("User Registration Failed")
                }
            }
    }
}
