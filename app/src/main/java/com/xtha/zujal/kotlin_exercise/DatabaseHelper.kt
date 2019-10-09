package com.xtha.zujal.kotlin_exercise

/**
 * Created by zujal on 17/01/2019.
 */

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.xtha.zujal.kotlin_exercise.Model.Product
import java.util.ArrayList


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        val DATABASE_NAME = "ProductList.db"
        val TABLE_NAME = "Products"
        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "AVAILABILITY"
        val COL_4 = "PRICE"
        val COL_5 = "QUANTITY"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AVAILABILITY TEXT,PRICE TEXT,QUANTITY TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // all data
    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TABLE_NAME", null)
        }

    //insert data
    fun insertData(product: Product): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, product.productName)
        contentValues.put(COL_3, product.productAvailability)
        contentValues.put(COL_4, product.productPrice)
        contentValues.put(COL_5, product.productQuantity)
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        Log.v("InsertedData", "$result")
        return (Integer.parseInt("$result") != -1)
    }

    // get user data
    fun GetUsers(): ArrayList<Product> {
        val db = this.writableDatabase
        val userList = ArrayList<Product>()
        val query = "SELECT id, name, availability, price, quantity FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {

            var _productId = cursor.getString(cursor.getColumnIndex(COL_1)).toInt()
            var _productName = cursor.getString(cursor.getColumnIndex(COL_2))
            var _productAvailability = cursor.getString(cursor.getColumnIndex(COL_3))
            var _productPrice = cursor.getString(cursor.getColumnIndex(COL_4))
            var _productQuantity = cursor.getString(cursor.getColumnIndex(COL_5))

            val user = Product(_productName, _productAvailability, _productPrice, _productQuantity, _productId)
            userList.add(user)
        }
        return userList
    }

    // Get User Details based on userid
    fun GetUserByUserId(userid: Int): ArrayList<Product> {
        val db = this.writableDatabase
        val userList = ArrayList<Product>()
        val query = "SELECT id, name, availability, price, quantity FROM $TABLE_NAME"
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_1, COL_2, COL_3, COL_4, COL_5),
            "$COL_1=?",
            arrayOf(userid.toString()),
            null,
            null,
            null,
            null
        )
        if (cursor.moveToNext()) {

            var _productId = cursor.getString(cursor.getColumnIndex(COL_1)).toInt()
            var _productName = cursor.getString(cursor.getColumnIndex(COL_2))
            var _productAvailability = cursor.getString(cursor.getColumnIndex(COL_3))
            var _productPrice = cursor.getString(cursor.getColumnIndex(COL_4))
            var _productQuantity = cursor.getString(cursor.getColumnIndex(COL_5))

            val user= Product(_productName, _productAvailability, _productPrice, _productQuantity, _productId)
            userList.add(user)
        }
        return userList
    }

    // update data
    fun updateData(product: Product): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, product.productName)
        contentValues.put(COL_3, product.productAvailability)
        contentValues.put(COL_4, product.productPrice)
        contentValues.put(COL_5, product.productQuantity)

        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(product.productId.toString()))
        return true
    }

    // delete data
    fun deleteData(id: String): Boolean? {
        val db = this.writableDatabase

        val result = db.delete(TABLE_NAME, "ID = ?", arrayOf(id)).toLong()
        db.close()
        Log.v("Data Deleted", "$result")
        return (Integer.parseInt("$result") != -1)
    }
}