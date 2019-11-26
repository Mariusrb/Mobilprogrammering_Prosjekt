package no.hiof.mariusrb.minkokebok.Screens

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_new_recipe.*
import no.hiof.mariusrb.minkokebok.Model.Recipe
import no.hiof.mariusrb.minkokebok.R
import java.util.*

class NewRecipe : AppCompatActivity() {

    private val CAMERA_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        supportActionBar?.title = "New Recipe"
        //checkUserPermission just as an demo on how I would do it
        checkUserPermission()
        selectphoto_button.setOnClickListener {
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
            val firebasadata = FirebaseDatabase.getInstance().getReference("/users").child(uid.toString())
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
    var selectedPhotoPath: String = ""

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapdrawable = BitmapDrawable(bitmap)
            selectphoto_button.setBackgroundDrawable(bitmapdrawable)
            uploadImageToFirebaseStorage()
        }

        //With camera accsess it would look like this:
       /* if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            val picture = data.extras?.get("data") as Bitmap1
            val bitmapdrawable = BitmapDrawable(picture)
            selectphoto_button.setBackgroundDrawable(bitmapdrawable)
            //Save picture to folder then convert it to URI
            val pictureuri = Uri.parse(filepath->picture)
            selectedPhotoUri = pictureuri

            uploadImageToFirebaseStorage()
        }

        */
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return
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
    private fun checkUserPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            /*
                   Code for the camera would look like this:
                   val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                   startActivityForResult(intent, CAMERA_PERMISSION_CODE)
                    */
        }
        else {
            requestCameraPermission()
        }
    }
    //Give the user a second chance with a message showing why access is needed.
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            AlertDialog.Builder(this)
                .setTitle("Camera permission needed")
                .setMessage("This permission is needed to add a picture of your recipe")
                .setPositiveButton("Ok"){dialog, which ->
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
                }

                .setNegativeButton("Cancel"){dialog, which ->
                    dialog.dismiss()
                }
                .create().show()
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}












