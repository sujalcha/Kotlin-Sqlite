package com.xtha.zujal.kotlin_exercise

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.xtha.zujal.kotlin_exercise.Model.Product

class ListAdapter(private val context: Context,
                  private val dataSource: ArrayList<Product>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.productlistview, parent, false)

        val productname = rowView.findViewById(R.id.productname) as TextView
        val productid = rowView.findViewById(R.id.productid) as TextView
        val productprice = rowView.findViewById(R.id.productprice) as TextView
        val productavailability = rowView.findViewById(R.id.productavailability) as TextView
        val productquantity = rowView.findViewById(R.id.productquantity) as TextView
        val productimage = rowView.findViewById(R.id.productimage) as ImageView

        val product = getItem(position) as Product

        productname.text = product.productName
        productprice.text = product.productPrice
        productquantity.text = product.productQuantity
        productavailability.text = product.productAvailability
        productid.text = product.productId.toString()

       // Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        val deleteproduct : Button= rowView.findViewById(R.id.deleteproduct)

        deleteproduct.setOnClickListener()
        {

            //delete from list first
            dataSource.removeAt(position)
            notifyDataSetChanged()

            //delete from database
            var databaseHelper = DatabaseHelper(this.context)
            databaseHelper.deleteData(product.productId.toString())
            this.notifyDataSetChanged()
        }

        val updateproduct : Button= rowView.findViewById(R.id.updateproduct)

        updateproduct.setOnClickListener()
        {
            val intent = Intent(this.context, UpdateProduct::class.java)
            intent.putExtra("productid",product.productId)
            context.startActivity(intent)
        }

        return rowView
    }
}