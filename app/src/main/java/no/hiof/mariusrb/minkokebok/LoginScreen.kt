package no.hiof.mariusrb.minkokebok

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
    }

    fun loginButton(view: View) {
        //TODO: Add firebase connection for handling users
        val changePage = Intent(this, MainActivity::class.java)
        startActivity(changePage)
    }

    fun signupButton(view: View) {
        //TODO("not implemented")
    }


}



