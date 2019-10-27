package no.hiof.mariusrb.minkokebok.Screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class NewRecipe : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        saveRecipeButton()
        supportActionBar?.title = "New Recipe"
    }


    private fun saveRecipeButton()    {
        newRecipeSaveButton.setOnClickListener {

            val recipeList: ArrayList<Recipe> = ArrayList()

            val title = newRecipeTitleText.text
            val description = newRecipeDescriptionText.text
            val recipe = recipeList.size + 1
            recipeList.add(Recipe(recipe, title.toString(), description.toString()))
            Log.d("TEST", "${Recipe.getRecipes()}")
            finish()
        }
    }
}











