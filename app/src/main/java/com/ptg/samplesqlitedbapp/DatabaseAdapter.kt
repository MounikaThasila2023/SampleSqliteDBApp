package com.ptg.samplesqlitedbapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class DatabaseAdapter(val context: Context) {

    private var dbHelper: DBHelper = DBHelper(context,null)

    fun insertData(name: String,age: String): Long{
        var result:Long=-1
        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
       val db=dbHelper.writableDatabase

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(DBHelper.NAME_COl, name)
        values.put(DBHelper.AGE_COL, age)

        // all values are inserted into database
        result=db.insert(DBHelper.TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
        return result
    }

    fun retreiveData() : Cursor?{
        val db=dbHelper.readableDatabase
        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null)
    }

}