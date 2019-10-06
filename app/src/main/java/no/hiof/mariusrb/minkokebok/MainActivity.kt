package no.hiof.mariusrb.minkokebok

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import no.hiof.mariusrb.minkokebok.Adapter.RecipeAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_recipe_list)
        listView.setBackgroundColor(Color.BLUE)

        listView.adapter = RecipeAdapter(this)
    }

}
