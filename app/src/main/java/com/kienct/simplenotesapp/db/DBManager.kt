package com.kienct.simplenotesapp.db

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager (
        context: Context?,
        name: String? = DATABASE_NAME,
        factory: SQLiteDatabase.CursorFactory? = null,
        version: Int = 1,
        errorHandler: DatabaseErrorHandler? = null
) : SQLiteOpenHelper(context, name, factory, version, errorHandler) {
    companion object {
        const val DATABASE_NAME = "TODO_LIST"
        const val ITEMS = "ITEMS_TABLE"
        const val TITLE = "TITLE"
        const val LAST_EDITED = "LAST_EDITED"
        const val ITEM_ID = "ID"
        const val CONTENT = "CONTENT"
        const val FAVORITE = "FAVORITE"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createProduct = "CREATE TABLE $ITEMS (" +
                "$ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FAVORITE INT, " +
                "$TITLE TEXT, " +
                "$CONTENT TEXT, " +
                "$LAST_EDITED TEXT)"
        p0?.execSQL(createProduct)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $ITEMS")
    }
}