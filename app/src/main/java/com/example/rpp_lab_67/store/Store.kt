package com.example.rpp_lab_67.store

import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

class Store {
    var products = mutableListOf<ProductCount>();
    var enabledProducts = mutableListOf<ProductCount>();

    fun addProduct(index : Int) : ProductCount {
        val productCount = ProductCount(Product(), 0)
        products.add(index, productCount)
        return productCount
    }

    fun removeProduct(index : Int){
         products.removeAt(index)
    }

    fun refreshEnabled(){
        enabledProducts.clear()
        for(i in 0 until products.size)
            if(products[i].getUnsoldCount() > 0)
                enabledProducts.add(products[i])
    }

}

val STORE = Store();

class ProductCount(val product: Product, count: Int){
    private var count = count
    private var willBuy = 0

    fun getCount() : Int{
       return max(count - willBuy,0);
    }

    fun getUnsoldCount() : Int{
        return count
    }

    fun addCount(change : Int){
        count += change
        if(count < 0)
            count = 0
    }

    fun tryBuy(buyAccept: BuyAccept){
        willBuy++
        Thread(Runnable { buy(buyAccept) }).start()
    }


    private fun buy(buyAccept: BuyAccept) {
        val random = Random(System.currentTimeMillis())
        Thread.sleep(abs(random.nextLong() % 3000) + 2000 )

        willBuy--
        if (count > 0){
            count--
            buyAccept.OnSuccess()
        }else {
            buyAccept.onError()
        }

        if(count <= 0)
            STORE.refreshEnabled()
    }


}