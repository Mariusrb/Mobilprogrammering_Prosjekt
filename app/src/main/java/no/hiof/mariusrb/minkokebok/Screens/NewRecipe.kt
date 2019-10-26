package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class NewRecipe : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        saveRecipeButton()

    }
    private fun saveRecipeButton(){
        newRecipeSaveButton.setOnClickListener {
            val title = newRecipeTitleText.text
            val description = newRecipeDescriptionText.text
            val index = "".toInt()
            //TODO("Find out how to fix index")


            Recipe.getRecipes().add(Recipe(index, title.toString(), description.toString()))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }

    }


}


