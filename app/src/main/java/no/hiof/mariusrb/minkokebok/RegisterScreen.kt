package no.hiof.mariusrb.minkokebok

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_screen.*

class RegisterScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        RegisterButton.setOnClickListener {
            performRegister()
        }

        registerAlreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister(){
        val email = registerEmail.text.toString()
        val password = registerPassword.text.toString()
        val username = registerUsername.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("LoginActivity", "Email is: " + email)
        Log.d("LoginActivity", "Password is: " + password)


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("Main", "Sucsesfully created user with uid: ${it.result?.user?.uid} ")
                val intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)

                saveUserToFirebaseDatabase()
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveUserToFirebaseDatabase(){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid, registerUsername.text.toString())

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Finally saved user to database")
            }
            .addOnFailureListener {
                //Logging
            }
    }
}
class User(val uid : String, val username : String)



