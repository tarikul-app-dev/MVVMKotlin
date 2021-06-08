package com.aleshatech.mvvmexample.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleshatech.mvvmexample.model.datamodel.Blog

class MainViewModel : ViewModel (){
    var lst = MutableLiveData<ArrayList<Blog>>()
    var newList = arrayListOf<Blog>()

    fun add(blog: Blog){
        newList.add(blog)
        lst.value = newList
    }

    fun remove(blog: Blog){
        newList.remove(blog)
        lst.value = newList
    }
}