package no.hiof.mariusrb.minkokebok.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import no.hiof.mariusrb.minkokebok.R

class RecipeDetailAdapter : RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailViewHolder>() {

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeDetailViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.recipe_detail_row, parent, false)

        return RecipeDetailViewHolder(customView)
    }

    override fun onBindViewHolder(holder: RecipeDetailViewHolder, position: Int) {
    }


    class RecipeDetailViewHolder(val customView: View) :
        RecyclerView.ViewHolder(customView) {
    }
}
