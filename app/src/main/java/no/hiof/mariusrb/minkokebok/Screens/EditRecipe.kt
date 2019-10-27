package no.hiof.mariusrb.minkokebok.Screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_recipe.*
import no.hiof.mariusrb.minkokebok.R

class EditRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        editedRecipeTitle.setText(intent.getStringExtra("EXTRA_RECIPE_TITLE"))
        editedRecipeDescription.setText(intent.getStringExtra("EXTRA_RECIPE_DESCRIPTION"))

        editedRecipeTextSaveButton.setOnClickListener {
            //TODO("Save changes")
        }
    }
}
