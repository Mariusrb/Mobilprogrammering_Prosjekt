package no.hiof.mariusrb.minkokebok.Screens


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import no.hiof.mariusrb.minkokebok.Directions_and_Args.RecipeDetailFragmentArgs
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

/**
 * A simple [Fragment] subclass.
 */
class RecipeDetailFragment : Fragment() {

    val descriptionText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments?.let { RecipeDetailFragmentArgs.fromBundle(it) }
        val recipe = Recipe.getRecipes()[arguments!!.uid]
        recipe_detail_title.text = recipe.title
        recipe_description_title.text = recipe.description
        //TODO:("Add picture taken by user after permission is granted")
        //  recipePictureView.setImageResource(recipe.picture)
        recipeDetailDeleteButton()
        recipeDetailEditButton.setOnClickListener {
            val intent = Intent(activity, EditRecipe::class.java)
            intent.putExtra("EXTRA_RECIPE_TITLE",recipe.title)
            intent.putExtra("EXTRA_RECIPE_DESCRIPTION", recipe.description)
            startActivity(intent)
        }
    }

    private fun recipeDetailDeleteButton(){
        recipeDetailDeleteButton.setOnClickListener {
            //TODO("Make studd dissapear")
            Log.d("TESTDELETE", "Stuff got deleted")
        }
    }
}

