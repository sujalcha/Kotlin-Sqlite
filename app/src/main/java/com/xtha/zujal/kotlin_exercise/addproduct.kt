package com.xtha.zujal.kotlin_exercise

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.xtha.zujal.kotlin_exercise.Model.Product

class addproduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        val buttonaddproduct: Button = findViewById(R.id.buttonaddproduct)

        buttonaddproduct.setOnClickListener {

            val databaseHelper = DatabaseHelper(this)

            val productname : EditText = findViewById(R.id.edttxtpname)
            val productavailability : EditText = findViewById(R.id.edttxtpavailability)
            val productprice : EditText = findViewById(R.id.edttxtpprice)
            val productquantity : EditText = findViewById(R.id.edttxtpquantity)

            val pname : String = productname.getText().toString()
            val pavailability : String = productavailability.getText().toString()
            val pprice : String = productprice.getText().toString()
            val pquantity : String = productquantity.getText().toString()

            val product = Product(pname,pavailability,pprice,pquantity)

            val status = databaseHelper.insertData(product)

            if (status){
                Toast.makeText(this,"Product Saved Successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, homescreen::class.java);
                startActivity(intent);
            }

            else {
                Toast.makeText(applicationContext, "Product could not be saved", Toast.LENGTH_LONG).show()

            }
        }


    }
}
