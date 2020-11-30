package com.kienct.simplenotesapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.kienct.simplenotesapp.R
import com.kienct.simplenotesapp.db.DAO
import com.kienct.simplenotesapp.entities.Item
import java.util.*

class CreateActivity : AppCompatActivity() {
    private var isFavourite = 0
    private lateinit var title: String
    private lateinit var content: String
    private lateinit var lastEdited: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        setSupportActionBar(findViewById(R.id.toolbar_create))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.item_save -> {
            title = findViewById<EditText>(R.id.etTitle).text.toString()
            content = findViewById<EditText>(R.id.etContent).text.toString()
            lastEdited = Calendar.getInstance().time.toString()
            val i = Item(null, title, lastEdited, content, isFavourite)
            DAO(this).insert(i)
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
            Log.d("ActionBar", i.isFavourite.toString())
            true
        }
        R.id.item_favorite -> {
            Log.d("ActionBar", "Favorite")
            isFavourite = when (isFavourite) {
                0 -> {
                    item.setIcon(R.drawable.ic_baseline_star_24)
                    1
                }
                else -> {
                    item.setIcon(R.drawable.ic_baseline_star_border_24)
                    0
                }
            }
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            Log.d("ActionBar", "Nah")
            super.onOptionsItemSelected(item)
        }
    }
}