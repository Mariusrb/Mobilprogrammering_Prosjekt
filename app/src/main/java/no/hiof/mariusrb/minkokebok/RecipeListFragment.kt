package no.hiof.mariusrb.minkokebok


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import no.hiof.mariusrb.minkokebok.Adapter.RecipeAdapter
import no.hiof.mariusrb.minkokebok.Directions_and_Args.RecipeListFragmentDirections
import no.hiof.mariusrb.minkokebok.Model.Recipe

/**
 * A simple [Fragment] subclass.
 */
class RecipeListFragment : Fragment(), View.OnClickListener {
    private var recipeList : ArrayList<Recipe> = Recipe.getRecipes()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater!!.inflate(R.layout.fragment_recipe_list, container,false)
        val btn : Button = view.findViewById(R.id.addRecipeButton)
        btn.setOnClickListener(this)
        return view
    }

    companion object{
        fun newInstance(): RecipeListFragment{
            return RecipeListFragment()
        }
    }

    override fun onClick(v: View?) {
        //TODO:("Implement new layout and logic to get and save user input into list")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecipeRecyclerView()
    }

    private fun setUpRecipeRecyclerView() {
        recipeRecyclerView.adapter = RecipeAdapter(recipeList, View.OnClickListener { view ->
            val position = recipeRecyclerView.getChildAdapterPosition(view)
            val clickedRecipe = recipeList[position]
            val action = RecipeListFragmentDirections.actionRecipeListToRecipeDetailFragment(clickedRecipe.uid)
            findNavController().navigate(action)
        })
        recipeRecyclerView.layoutManager = GridLayoutManager(context, 1)
    }
}

