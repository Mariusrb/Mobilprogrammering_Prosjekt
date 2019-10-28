package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recipe_row.view.*
import no.hiof.mariusrb.minkokebok.Login.RegisterScreen
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyUserIsLoggedIn()
        supportActionBar?.title = "My Recipe Book"
        addNewRecipeButton.setOnClickListener {
            val intent = Intent(this, NewRecipe::class.java)
            startActivity(intent)
        }
           loadDatabase()

    }

    private fun loadDatabase() {
        val firebasadata = FirebaseDatabase.getInstance().getReference()
        val availableRecipes: List<Recipe> = mutableListOf(
            Recipe("", "Bolle", "gode"),
            Recipe("", "Pizza", "Hjemmelaget"),
            Recipe("", "Pasta", "Italiensk")
        )
        availableRecipes.forEach {
            val key = firebasadata.child("recipe").push().key
            it.uid = key.toString()
            firebasadata.child("recipe").child(key.toString()).setValue(it)
        }

        fetchRecipes()
    }

    private fun fetchRecipes() {
        //   val firebasadata = FirebaseDatabase.getInstance().getReference("/users")
        //   val currentUser = FirebaseAuth.getInstance().currentUser
        //   val userkey = currentUser?.uid
        //   firebasadata.child("/users").child(userkey!!)
        //       .addListenerForSingleValueEvent(object : ValueEventListener {
        val ref = FirebaseDatabase.getInstance().getReference("/recipe")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach {
                    val recipe = it.getValue(Recipe::class.java)
                    adapter.add(UserItem(recipe!!))
                }
                recyclerview_recipes.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, RegisterScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sign_out -> {
                Log.d("MainActivity", "Goodbye ${FirebaseAuth.getInstance().uid}")
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterScreen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            R.id.menu_home -> {
                val intent = Intent(this, MainActivity::class.java)
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

class UserItem(val recipe: Recipe) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.recipe_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.recipe_title_TextView.text = recipe.title
        viewHolder.itemView.recipe_description_TextView.text = recipe.description
    }
}