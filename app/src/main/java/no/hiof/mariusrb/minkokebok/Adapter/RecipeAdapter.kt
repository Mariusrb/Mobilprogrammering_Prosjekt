package no.hiof.mariusrb.minkokebok.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import no.hiof.mariusrb.minkokebok.R

class RecipeAdapter(context: Context) : BaseAdapter() {

    private val mContext: Context

    private val recipes = arrayListOf<String>(
        "Pizza", "Boller", "Pasta", "Lasagne", "Laks"
    )

    init {
        this.mContext = context
    }
    //decides how many rows that are rendering
    override fun getCount(): Int {
        return recipes.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return "Test string"
    }

    //render each row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutinflater = LayoutInflater.from(mContext)

        val mainRow = layoutinflater.inflate(R.layout.recipe_main_row, parent, false)
        val positionTextView = mainRow.findViewById<TextView>(R.id.position_textview)
        positionTextView.text = "Oppskrift nummer: $position"
        val titleTextView = mainRow.findViewById<TextView>(R.id.name_textview)
        titleTextView.text = recipes.get(position)

        return mainRow


    }

}