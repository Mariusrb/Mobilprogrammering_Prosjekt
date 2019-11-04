package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import no.hiof.mariusrb.minkokebok.R

class RecipeDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        val recipetitle = intent.getStringExtra("EXTRA_RECIPE_TITLE")
        val recipedescription = intent.getStringExtra("EXTRA_RECIPE_DESCRIPTION")
        val recipeuid = intent.getStringExtra("EXTRA_RECIPE_UID")
        val recipepicture = intent.getStringExtra("EXTRA_RECIPE_PICTURE")
        onCreated()

        editRecipeButton.setOnClickListener {
            val intent = Intent(this, EditRecipe::class.java)
            intent.putExtra("EXTRA_RECIPE_TITLE", recipetitle)
            intent.putExtra("EXTRA_RECIPE_DESCRIPTION", recipedescription)
            intent.putExtra("EXTRA_RECIPE_UID", recipeuid)
            intent.putExtra("EXTRA_RECIPE_PICTURE", recipepicture)
            startActivity(intent)
        }
            deleteRecipeButton.setOnClickListener {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val userkey = currentUser?.uid
                val ref = FirebaseDatabase.getInstance().getReference("/users").child(userkey!!).child("/recipe").child(recipeuid)
                ref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        p0.ref.removeValue()
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                }
                )
                finish()
            }
    }
    private fun onCreated(){
            recipeDetailTitle.setText(intent.getStringExtra("EXTRA_RECIPE_TITLE"))
            recipeDetailDescription.setText(intent.getStringExtra("EXTRA_RECIPE_DESCRIPTION"))
            val picture = intent.getStringExtra("EXTRA_RECIPE_PICTURE")
            val realpicture = Uri.parse(intent.getStringExtra("EXTRA_RECIPE_PICTURE"))

        //TODO:("Make the picture work propperly without crashing the app. The line below crashes the app.")
        //    recipeListImageView.setImageURI(realpicture)
            supportActionBar?.title = intent.getStringExtra("EXTRA_RECIPE_TITLE")
    }
}
