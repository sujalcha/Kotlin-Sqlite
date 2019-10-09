package com.xtha.zujal.kotlin_exercise

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.xtha.zujal.kotlin_exercise.Model.Product
import java.util.ArrayList

class UpdateProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        val productid=intent.getIntExtra("productid",99999999)

        val databaseHelper  = DatabaseHelper(this)

        val product: ArrayList<Product> = databaseHelper.GetUserByUserId(productid)

        val productname : EditText = findViewById(R.id.edttxtpname)
        val productavailability : EditText = findViewById(R.id.edttxtpavailability)
        val productprice : EditText = findViewById(R.id.edttxtpprice)
        val productquantity : EditText = findViewById(R.id.edttxtpquantity)

        val pname : String = product[0].productName
        val pavailability : String = product[0].productAvailability
        val pprice : String = product[0].productPrice
        val pquantity : String = product[0].productQuantity

        productname.setText(pname)
        productavailability.setText(pavailability)
        productprice.setText(pprice)
        productquantity.setText(pquantity)

        val buttonupdateproduct: Button = findViewById(R.id.buttonupdateproduct)

        buttonupdateproduct.setOnClickListener {

            val updatedpname : String = productname.getText().toString()
            val updatedpavailability : String = productavailability.getText().toString()
            val updatedpprice : String = productprice.getText().toString()
            val updatedpquantity : String = productquantity.getText().toString()

            val updatedproduct = Product(updatedpname,updatedpavailability,updatedpprice,updatedpquantity,productid)
            val status = databaseHelper.updateData(updatedproduct)

            if (status){
                Toast.makeText(this,"Updated Successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, homescreen::class.java)
                startActivity(intent);
            }

            else {
                Toast.makeText(applicationContext, "Product could not be updated", Toast.LENGTH_LONG).show()

            }
        }

    }
}
