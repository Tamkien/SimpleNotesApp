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
import kotlinx.android.synthetic.main.activity_update.*
import java.util.*
import kotlin.properties.Delegates

class UpdateActivity : AppCompatActivity() {
    private var isFavourite = 0
    private lateinit var title: String
    private lateinit var content: String
    private lateinit var lastEdited: String
    private lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        editingTitle.setText(intent.getStringExtra("Title"))
        editingContent.setText(intent.getStringExtra("Content"))
        isFavourite = intent.getIntExtra("IsFavorite", 0)
        id = intent.getStringExtra("ID").toString()
        setSupportActionBar(findViewById(R.id.toolbar_create))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(
            when (isFavourite) {
                0 -> R.menu.menu_create
                else -> R.menu.menu_favorite_item
            }, menu
        )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.item_save -> {
            title = findViewById<EditText>(R.id.editingTitle).text.toString()
            content = findViewById<EditText>(R.id.editingContent).text.toString()
            lastEdited = Calendar.getInstance().time.toString()
            val i = Item(id.toInt(), title, lastEdited, content, isFavourite)
            DAO(this).update(i)
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
            Log.d("ActionBar", i.lastEdited.toString())
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
        R.id.item_share -> {
//            Log.d("ActionBar", Calendar.getInstance().time.toString())
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