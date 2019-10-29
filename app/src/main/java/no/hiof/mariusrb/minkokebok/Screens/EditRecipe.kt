package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class EditRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        editedRecipeTitle.setText(intent.getStringExtra("EXTRA_RECIPE_TITLE"))
        editedRecipeDescription.setText(intent.getStringExtra("EXTRA_RECIPE_DESCRIPTION"))
        val uid = intent.getStringExtra("EXTRA_RECIPE_UID")

        editedRecipeTextSaveButton.setOnClickListener {
            val completedTitle = editedRecipeTitle.text.toString()
            val completedDescription = editedRecipeDescription.text.toString()
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userkey = currentUser?.uid
            val ref = FirebaseDatabase.getInstance().getReference("/users").child(userkey!!).child("/recipe").child(uid)
            val completeEditedRecipe = Recipe(uid,completedTitle, completedDescription )
            ref.setValue(completeEditedRecipe)

            val backintent = Intent(this, MainActivity::class.java)
            startActivity(backintent)
            finish()
        }
    }
}
