package no.hiof.mariusrb.minkokebok.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_detail_row.view.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class RecipeDetailAdapter(private val items : ArrayList<Recipe>, var clickListener: View.OnClickListener) : RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailViewHolder>() {

  //  lateinit var clicklistener : View.OnClickListener
 //   private val items : ArrayList<Recipe>? = null

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeDetailViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.recipe_detail_row, parent, false)

        return RecipeDetailViewHolder(customView)
    }

    override fun onBindViewHolder(holder: RecipeDetailViewHolder, position: Int) {
        val recipe : Recipe? = null
        holder.customView.recipe_detail_title.setText(recipe?.title)
        holder.customView.recipe_description_title.setText(recipe?.description)


        val currentRecipe = items.get(position)

        holder.bind(currentRecipe, clickListener)
        }

    class RecipeDetailViewHolder(val customView: View) : RecyclerView.ViewHolder(customView) {

        private val recipeTitleTextView : TextView = customView.recipe_detail_title
        private val recipeDescriptionTextView : TextView = customView.recipe_description_title

        fun bind(item : Recipe, clickListener: View.OnClickListener){

            recipeTitleTextView.text = item.title
            recipeDescriptionTextView.text = item.description

            this.customView.setOnClickListener(clickListener)

            //TODO("Make this connection work propperly")

        }
    }
}

