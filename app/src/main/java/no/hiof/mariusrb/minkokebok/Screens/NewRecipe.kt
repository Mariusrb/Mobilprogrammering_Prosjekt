package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_new_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import no.hiof.mariusrb.minkokebok.Adapter.RecipeAdapter
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class NewRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        saveRecipeButton()

    }


    private fun saveRecipeButton() {
        newRecipeSaveButton.setOnClickListener {

            val recipeList: ArrayList<Recipe> = Recipe.getRecipes()

            val title = newRecipeTitleText.text
            val description = newRecipeDescriptionText.text
            val recipe = recipeList.size + 1
            //TODO("Find out how to fix index")

           

            recipeList.add(Recipe(recipe, title.toString(), description.toString()))
            finish()

        }

    }
}








