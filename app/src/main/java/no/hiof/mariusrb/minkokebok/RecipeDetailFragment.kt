package no.hiof.mariusrb.minkokebok


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import no.hiof.mariusrb.minkokebok.Directions_and_Args.RecipeDetailFragmentArgs
import no.hiof.mariusrb.minkokebok.Model.Recipe

/**
 * A simple [Fragment] subclass.
 */
class RecipeDetailFragment : Fragment() {

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
    }
}
