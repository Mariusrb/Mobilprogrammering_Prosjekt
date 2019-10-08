package no.hiof.mariusrb.minkokebok.Model

import java.io.Serializable

data class Recipe(val uid: Int, var title: String, var description: String) : Serializable {

    companion object {
        fun getRecipes(): ArrayList<Recipe> {
            val data = ArrayList<Recipe>()

            var titles = arrayOf(
                "Pizza",
                "Boller",
                "Pasta",
                "Bolognese"
            )
            var description = arrayOf(
                "Hjemmelaget pizza",
                "Beste bollene",
                "God lasagne",
                "Beste bolognesen"
            )
            titles.forEachIndexed { index, title ->
                val aRecipe = Recipe(index, title, title + "\n" + description.get(index))

                data.add(aRecipe)
            }
            return data
        }
    }
}