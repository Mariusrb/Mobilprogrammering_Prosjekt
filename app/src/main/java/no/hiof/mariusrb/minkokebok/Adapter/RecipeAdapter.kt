package no.hiof.mariusrb.minkokebok.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class RecipeAdapter(context: Context) : BaseAdapter() {

    private val mContext: Context

    init {
        this.mContext = context
    }



    override fun getCount(): Int {
        return 5
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return "Test string"
    }

    //render each row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(mContext)
        textView.text = "Testing"
        return textView
    }

}