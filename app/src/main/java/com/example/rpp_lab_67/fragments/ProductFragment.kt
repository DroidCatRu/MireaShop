package com.example.rpp_lab_67.fragments


import androidx.fragment.app.Fragment
import com.example.rpp_lab_67.store.ProductCount

abstract class ProductFragment(var product: ProductCount, var adapter : ProductAdapter) : Fragment() {
    var mustBeRefreshed = true

    abstract fun refresh()
    abstract fun clear()
}