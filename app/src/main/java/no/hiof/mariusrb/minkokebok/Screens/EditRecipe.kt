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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R
import java.util.*

class EditRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)
        val uid = intent.getStringExtra("EXTRA_RECIPE_UID")
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userkey = currentUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/users").child(userkey!!).child("/recipe").child(uid)

        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val database = p0.getValue(Recipe::class.java)
                editedRecipeTitle.setText(database!!.title)
                editedRecipeDescription.setText(database.description)
                val picture = Uri.parse(database.recipephoto)
                Picasso.get().load(picture).into(editedRecipePicture)
                oldPicture = database.recipephoto
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        editedRecipePicture.setOnClickListener {
            Log.d("TEST", "Supertest")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        editedRecipeTextSaveButton.setOnClickListener {
            val completedTitle = editedRecipeTitle.text.toString()
            val completedDescription = editedRecipeDescription.text.toString()
            val completedPicture = selectedPhotoPath
            val ref2 = FirebaseStorage.getInstance().getReference("/images").child(oldPicture)
            ref2.delete()
                .addOnCompleteListener {
                    Log.d("Photo", "Image deleted: $oldPicture")
                }
                .addOnFailureListener {
                    Log.d("Photo", "Image not deleted: $oldPicture")
                }

            if(selectedPhotoPath == "") {
                val completeEditedRecipe =
                    Recipe(uid, completedTitle, completedDescription, oldPicture )
                ref.setValue(completeEditedRecipe)
            }
            else{
                val completeEditedRecipe =
                Recipe(uid, completedTitle, completedDescription, completedPicture )
                ref.setValue(completeEditedRecipe)
            }

            val backintent = Intent(this, MainPage::class.java)
            startActivity(backintent)
            finish()
        }
    }

    var oldPicture: String = ""
    var selectedPhotoUri: Uri? = null
    var selectedPhotoPath:String = ""

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            Log.d("NewRecipe", "Photo was selected")

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapdrawable = BitmapDrawable(bitmap)
            editedRecipePicture.setBackgroundDrawable(bitmapdrawable)
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
