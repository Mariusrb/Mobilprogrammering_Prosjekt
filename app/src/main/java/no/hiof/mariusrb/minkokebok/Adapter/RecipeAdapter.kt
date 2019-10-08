package no.hiof.mariusrb.minkokebok.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class RecipeAdapter(private val items : ArrayList<Recipe>, var clickListener: View.OnClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecipeAdapter.RecipeViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)

        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        val currentRecipe = items[position]

        holder.bind(currentRecipe, clickListener)

    }

    class RecipeViewHolder(view : View) : RecyclerView.ViewHolder(view){
 //       private val recipePictureImageView : ImageView = view.recipePictureView
        private val recipeTitleTextView : TextView = view.recipe_title
     //   private val recipeDescriptionTextView : TextView = view.recipe_description_title

        fun bind(item:Recipe, clickListener: View.OnClickListener){

          //  recipePictureImageView.setImageResource()
            recipeTitleTextView.text = item.title
        //    recipeDescriptionTextView.text = item.description

            this.itemView.setOnClickListener(clickListener)
        }




    }



}





