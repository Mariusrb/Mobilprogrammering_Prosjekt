package no.hiof.mariusrb.minkokebok.Screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_google_search.*
import no.hiof.mariusrb.minkokebok.R

class GoogleSearch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_search)
    }

    fun googleSearch(view: View) {
        val query = SearchText.text.toString()
        val uri = Uri.parse("https://www.google.com/search?q="+query)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
