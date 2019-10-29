package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import no.hiof.mariusrb.minkokebok.Login.RegisterScreen
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.Model.UserItem
import no.hiof.mariusrb.minkokebok.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "My Recipe Book"
        addnewRecipeButton()
        verifyUserIsLoggedIn()
        fetchRecipes()
    }

    private fun addnewRecipeButton(){
        addNewRecipeButton.setOnClickListener {
            val intent = Intent(this, NewRecipe::class.java)
            startActivity(intent)
        }
    }

    private fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, RegisterScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun fetchRecipes() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userkey = currentUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/users").child(userkey!!).child("/recipe")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach {
                    val recipe = it.getValue(Recipe::class.java)
                    adapter.add(UserItem(recipe!!))
                }
                adapter.setOnItemClickListener { item, view ->
                    val userItem = item as UserItem
                    val intent = Intent(view.context, RecipeDetail::class.java)
                    intent.putExtra("EXTRA_RECIPE_TITLE", item.recipe.title)
                    intent.putExtra("EXTRA_RECIPE_DESCRIPTION", item.recipe.description)
                    intent.putExtra("EXTRA_RECIPE_UID", item.recipe.uid)
                    startActivity(intent)
                }
                recyclerview_recipes.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {
                //Logging
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterScreen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        fetchRecipes()
    }
}
