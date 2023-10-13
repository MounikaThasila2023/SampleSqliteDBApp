package com.ptg.samplesqlitedbapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context,
    DATABASE_NAME,factory, DATABASE_VERSION,null) {

    override fun onCreate(db: SQLiteDatabase?) {
        // we are calling sqlite
        // method for executing our query
          db?.execSQL(CREATE_TABLE)

        Toast.makeText(context,"oncreate called",Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // this method is to check if table already exists
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        //db?.execSQL("ALTER TABLE "+ TABLE_NAME + " ADD "+ ADDRESS_COL + " Text")
        Toast.makeText(context,"onupgrade called",Toast.LENGTH_SHORT).show()
        onCreate(db)

    }


    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "SampleDB"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "user"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "name"

        // below is the variable for age column
        val AGE_COL = "age"

        val ADDRESS_COL = "address"


        // below is a sqlite query, where column names
        // along with their data types is given
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
               // ADDRESS_COL + " TEXT," +
                AGE_COL + " TEXT" + ")")
    }

}