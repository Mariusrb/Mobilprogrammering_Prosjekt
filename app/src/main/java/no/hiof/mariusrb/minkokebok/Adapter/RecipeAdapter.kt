package no.hiof.mariusrb.minkokebok.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_list.view.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

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
    }


    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}




