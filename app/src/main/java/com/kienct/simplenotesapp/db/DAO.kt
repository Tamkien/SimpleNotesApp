package com.kienct.simplenotesapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kienct.simplenotesapp.entities.Item

class DAO(context: Context) {
    private var db: SQLiteDatabase

    init {
        val dbManager = DBManager(context)
        db = dbManager.writableDatabase
    }

    fun insert(item: Item): Long {
        val values = ContentValues()
        values.put(DBManager.TITLE, item.title)
        values.put(DBManager.LAST_EDITED, item.lastEdited)
        values.put(DBManager.CONTENT, item.content)
        values.put(DBManager.FAVORITE, item.isFavourite)
        return db.insert(DBManager.ITEMS, null, values)
    }

    fun delete(id: String): Int {
        return db.delete(DBManager.ITEMS, "${DBManager.ITEM_ID}=?", arrayOf(id))
    }

    fun update(item: Item): Int {
        val values = ContentValues()
        values.put(DBManager.CONTENT, item.content)
        values.put(DBManager.LAST_EDITED, item.lastEdited)
        values.put(DBManager.TITLE, item.title)
        values.put(DBManager.FAVORITE, item.isFavourite)
        return db.update(
                DBManager.ITEMS,
                values,
                "${DBManager.ITEM_ID}=?",
                arrayOf(item.id.toString())
        )
    }

    fun selectAll(): MutableList<Item> {
        val output = arrayListOf<Item>()
        val sql = "select * from ${DBManager.ITEMS}"
        val cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DBManager.ITEM_ID))
            val title = cursor.getString(cursor.getColumnIndex(DBManager.TITLE))
            val content = cursor.getString(cursor.getColumnIndex(DBManager.CONTENT))
            val lastEdited = cursor.getString(cursor.getColumnIndex(DBManager.LAST_EDITED))
            val favored = cursor.getInt(cursor.getColumnIndex(DBManager.FAVORITE))
            output.add(Item(id, title, content, lastEdited, favored))
        }
        cursor.close()
        return output
    }
}