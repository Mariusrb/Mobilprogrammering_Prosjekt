package no.hiof.mariusrb.minkokebok.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_list.view.*
import no.hiof.mariusrb.minkokebok.MainActivity
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R
import no.hiof.mariusrb.minkokebok.RecipeDetailActivity

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.CustomViewHolder>() {

    val recipeTitles = listOf<String>("Pizza", "Lasagne", "Bolognese", "Boller", "Kake", "Smør")


    override fun getItemCount(): Int {
        //Number of items
        return recipeTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recipe_list, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val recipeTitles = recipeTitles.get(position)
        holder.view.Recipe_title_textView.text = recipeTitles

        holder.recipe = recipeTitles
    }

    class CustomViewHolder(val view: View, var recipe: String? = null) : RecyclerView.ViewHolder(view) {

        companion object{
            val RECIPE_TITLE_KEY = "RECIPE_TITLE"
        }

        init {
            view.setOnClickListener {

                val intent = Intent(view.context, RecipeDetailActivity::class.java)

                intent.putExtra(RECIPE_TITLE_KEY, recipe)

                view.context.startActivity(intent)
            }
        }
    }
}





