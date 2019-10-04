package com.xtha.zujal.kotlin_exercise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xtha.zujal.kotlin_exercise.Model.Product

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Printing your first Hello Worrld Text
        println( "This is a Hello Word Text")

        // Instantiating your first object from class product
        var product = Product()
        product.productName = "Chair"

        println(product.productName)
        println("This is " + product.productName + " which is a product name")
        println("This is ${product.productName} which is a product name")
    }
}
