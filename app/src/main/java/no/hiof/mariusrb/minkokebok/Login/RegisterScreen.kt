package no.hiof.mariusrb.minkokebok.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_screen.*
import no.hiof.mariusrb.minkokebok.Model.User
import no.hiof.mariusrb.minkokebok.R
import no.hiof.mariusrb.minkokebok.Screens.MainPage

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
            Toast.makeText(this, "Please enter text in Email and Password", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                saveUserToFirebaseDatabase()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveUserToFirebaseDatabase(){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid, registerUsername.text.toString())

        ref.setValue(user)
            .addOnSuccessListener {
                val intent = Intent(this, MainPage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                //Logging
            }
    }
}






