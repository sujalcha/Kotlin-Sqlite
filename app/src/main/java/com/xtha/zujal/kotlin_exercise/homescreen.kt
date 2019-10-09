package com.xtha.zujal.kotlin_exercise

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.xtha.zujal.kotlin_exercise.Model.Product

class homescreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        //button that adds product
        val btnaddproduct: Button = findViewById(R.id.btnaddproduct)

        //button when clicked sendts to add product activity
        btnaddproduct.setOnClickListener {
            val intent = Intent(applicationContext, addproduct::class.java);
            startActivity(intent);
        }

        // initializing database helper class and getting users
        var databaseHelper = DatabaseHelper(this)
        val productlist: ArrayList<Product> = databaseHelper.GetUsers()

        //creating blank arraylist of products
        val listItems: ArrayList<Product> = arrayListOf<Product>()

        //filling list with products
        for (i in 0 until productlist.size) {
            val product: Product = productlist[i]
            listItems.add(product)
        }

        //creating adapter for listview
        val adapter = ListAdapter(this, listItems)

        var listView: ListView = findViewById(R.id.lstvwproducts)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}
