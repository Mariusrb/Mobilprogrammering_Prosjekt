package no.hiof.mariusrb.minkokebok.Model

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.recipe_row.view.*
import no.hiof.mariusrb.minkokebok.R

class UserItem(val recipe: Recipe) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.recipe_row
    }
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.recipe_title_TextView.text = recipe.title
        viewHolder.itemView.recipe_description_TextView.text = recipe.description
    }
}