package no.hiof.mariusrb.minkokebok.Screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class NewRecipe : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        supportActionBar?.title = "New Recipe"
        val title = newRecipeTitleText.text.toString()
        val description = newRecipeDescriptionText.text.toString()

        newRecipeSaveButton.setOnClickListener {
            val changedTitle = newRecipeTitleText.text.toString()
            val changedDescription = newRecipeDescriptionText.text.toString()

            val firebaseuser = FirebaseAuth.getInstance().currentUser
            val uid = firebaseuser?.uid
            val firebasadata = FirebaseDatabase.getInstance().getReference("/users").child(uid.toString())


            val newRecipe: List<Recipe> = mutableListOf(
                Recipe("", changedTitle, changedDescription)
            )
            newRecipe.forEach {
                val key = firebasadata.child("recipe").push().key
                it.uid = key.toString()
                firebasadata.child("recipe").child(key.toString()).setValue(it)
                finish()

            }
        }
    }
}











