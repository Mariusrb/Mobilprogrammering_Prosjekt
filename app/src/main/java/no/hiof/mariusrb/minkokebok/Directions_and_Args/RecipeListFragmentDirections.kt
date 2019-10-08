package no.hiof.mariusrb.minkokebok.Directions_and_Args

import android.os.Bundle
import androidx.navigation.NavDirections
import no.hiof.mariusrb.minkokebok.R

class RecipeListFragmentDirections private constructor() {
    private data class ActionRecipeListToRecipeDetailFragment(val uid: Int) : NavDirections{
        override fun getActionId(): Int =
            R.id.action_recipeListFragment_to_recipeDetailFragment

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putInt("uid", this.uid)
            return result
        }
    }

    companion object{
        fun actionRecipeListToRecipeDetailFragment(uid: Int) : NavDirections =
            ActionRecipeListToRecipeDetailFragment(
                uid
            )
    }
}