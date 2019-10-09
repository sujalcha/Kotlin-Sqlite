package com.xtha.zujal.kotlin_exercise. Model


class  Product constructor( var _productName : String = "", var _productAvailability : String = "", var _productPrice : String= "", var _productQuantity : String = "", var _productId : Int = 0 ){

    var productId : Int
    var productName : String
    var productAvailability : String
    var productPrice : String
    var productQuantity : String

    init {

         this.productId = _productId
         this.productName  = _productName
         this.productAvailability = _productAvailability
         this.productPrice = _productPrice
         this.productQuantity  = _productQuantity

//        println("productId = $productId")
//        println("productName = $productName")
//        println("productAvailability = $productAvailability")
//        println("productPrice = $productPrice")
//        println("productQuantity = $productQuantity")
    }


    constructor(pname: String, pAvailability: String, pPrice: String, pQuantity: String) : this(pname)
    {
        this.productId = _productId
        this.productName = pname
        this.productAvailability = pAvailability
        this.productPrice = pPrice
        this.productQuantity = pQuantity
    }

    override fun toString(): String {
        return "productId:" + this.productId +
                " productName: " + this.productName +
                " productAvailability: " + this.productAvailability +
                " productPrice : " + this.productPrice +
                " productQuantity:"+ this.productQuantity
    }
}