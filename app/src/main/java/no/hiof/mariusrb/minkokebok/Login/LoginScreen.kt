package no.hiof.mariusrb.minkokebok.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import no.hiof.mariusrb.minkokebok.Screens.MainPage


class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(no.hiof.mariusrb.minkokebok.R.layout.activity_login)
        loginButton.setOnClickListener {
            performLogin()
        }
        backToRegistrationText.setOnClickListener {
            finish()
        }
    }

    private fun performLogin() {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener
                        val intent = Intent(this, MainPage::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
            }
        }
}

