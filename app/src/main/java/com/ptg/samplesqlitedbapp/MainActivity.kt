package com.ptg.samplesqlitedbapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edUserName:EditText
    private lateinit var edAge:EditText
    private lateinit var btnAdd: Button
    private lateinit var btnPrint: Button
    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    private lateinit var dbHelper: DBHelper
    private lateinit var dbAdapter: DatabaseAdapter

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edUserName=findViewById(R.id.edUserName)
        edAge=findViewById(R.id.edAge)
        btnAdd=findViewById(R.id.btnAdd)
        btnPrint=findViewById(R.id.btnPrint)
        txtName=findViewById(R.id.Name)
        txtAge=findViewById(R.id.Age)

        // below we have created
        // a new DBHelper class,
        // and passed context to it
        //dbHelper=DBHelper(this,null)
        //val db=dbHelper.writableDatabase

        dbAdapter= DatabaseAdapter(this)


        btnAdd.setOnClickListener {


            // creating variables for values
            // in name and age edit texts
            val name = edUserName.text.toString()
            val age = edAge.text.toString()

            // calling method to add
            // name to our database
            //addName(name, age)
            val result=dbAdapter.insertData(name,age)
            if(result != -1L){
                Toast.makeText(this, name + " is added to pos $result", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Unable to insert", Toast.LENGTH_LONG).show()
            }

            // at last, clearing edit texts
            edUserName.text.clear()
            edAge.text.clear()
        }

        btnPrint.setOnClickListener {
            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            //val cursor = getName()
            val cursor = dbAdapter.retreiveData()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            txtName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            txtAge.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                txtName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                txtAge.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }

    // This method is for adding data in our database
    fun addName(name : String, age : String ){

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
        db.insert(DBHelper.TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
        // Toast to message on the screen
        Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db=dbHelper.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null)
    }

}