package com.kienct.simplenotesapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kienct.simplenotesapp.R
import com.kienct.simplenotesapp.db.DAO
import com.kienct.simplenotesapp.entities.ItemAdapter
import kotlinx.android.synthetic.main.activity_read.*


class ReadActivity : AppCompatActivity() {
    private lateinit var dao: DAO
    private lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        dao = DAO(this)
        itemAdapter = ItemAdapter(dao.selectAll())
        item_container.layoutManager = LinearLayoutManager(this)
        item_container.adapter = itemAdapter
        setSupportActionBar(findViewById(R.id.toolbar_create))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            Log.d("ActionBar", "Setting")
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