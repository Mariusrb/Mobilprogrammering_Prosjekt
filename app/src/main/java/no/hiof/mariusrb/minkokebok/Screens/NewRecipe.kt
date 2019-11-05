package no.hiof.mariusrb.minkokebok.Screens

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_new_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R
import java.util.*

class NewRecipe : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        supportActionBar?.title = "New Recipe"

        selectphoto_button.setOnClickListener {
            Log.d("TEST", "Supertest")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }

        newRecipeSaveButton.setOnClickListener {

            val changedTitle = newRecipeTitleText.text.toString()
            val changedDescription = newRecipeDescriptionText.text.toString()
            val changedImage = selectedPhotoPath
            val firebaseuser = FirebaseAuth.getInstance().currentUser
            val uid = firebaseuser?.uid
            val firebasadata =
                FirebaseDatabase.getInstance().getReference("/users").child(uid.toString())

            val newRecipe: List<Recipe> = mutableListOf(
                    Recipe("", changedTitle, changedDescription, changedImage)
                )
                newRecipe.forEach {
                    val key = firebasadata.child("recipe").push().key
                    it.uid = key.toString()
                    firebasadata.child("recipe").child(key.toString()).setValue(it)
                    Log.d("TEST", changedImage)
                    finish()
                }

            }
        }

    var selectedPhotoUri: Uri? = null
    var selectedPhotoPath:String = ""

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            Log.d("NewRecipe", "Photo was selected")

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapdrawable = BitmapDrawable(bitmap)
            selectphoto_button.setBackgroundDrawable(bitmapdrawable)
            uploadImageToFirebaseStorage()
        }
    }

    private fun uploadImageToFirebaseStorage(){
        if(selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("Photo", "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("Photo", "File Location: $it")
                    selectedPhotoPath = it.toString()

                }
            }
    }
}












