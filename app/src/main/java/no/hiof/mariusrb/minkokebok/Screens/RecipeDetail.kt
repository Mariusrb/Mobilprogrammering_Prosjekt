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
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class RecipeDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        val recipetitle = intent.getStringExtra("EXTRA_RECIPE_TITLE")
        val recipeuid = intent.getStringExtra("EXTRA_RECIPE_UID")
        supportActionBar?.title = recipetitle
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userkey = currentUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/users").child(userkey!!).child("/recipe").child(recipeuid)


        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val database = p0.getValue(Recipe::class.java)
                recipeDetailTitle.text = database!!.title
                val picture = Uri.parse(database.recipephoto)
                recipeDetailImage.setImageURI(picture)
                recipeDetailDescription.text = database.description

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })



        editRecipeButton.setOnClickListener {
            val intent = Intent(this, EditRecipe::class.java)
       //     intent.putExtra("EXTRA_RECIPE_TITLE", recipetitle)
       //     intent.putExtra("EXTRA_RECIPE_DESCRIPTION", recipedescription)
            intent.putExtra("EXTRA_RECIPE_UID", recipeuid)
       //     intent.putExtra("EXTRA_RECIPE_PICTURE", recipepicture)
            startActivity(intent)
        }
            deleteRecipeButton.setOnClickListener {

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
}
