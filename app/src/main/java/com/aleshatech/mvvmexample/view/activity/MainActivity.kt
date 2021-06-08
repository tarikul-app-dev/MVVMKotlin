package com.aleshatech.mvvmexample.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleshatech.mvvmexample.R
import com.aleshatech.mvvmexample.databinding.ActivityMainBinding
import com.aleshatech.mvvmexample.model.datamodel.Blog
import com.aleshatech.mvvmexample.view.adapter.NoteRecyclerAdapter
import com.aleshatech.mvvmexample.view.viewmodel.MainViewModel
import com.aleshatech.mvvmexample.view.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)

        binding.button.setOnClickListener(){
                addData()
        }

        initialiseAdapter()
    }

    private fun initialiseAdapter(){
        binding.recycler.layoutManager = viewManager
        observeData()
    }
    fun observeData(){
        viewModel.lst.observe(this, Observer{
            Log.i("data",it.toString())
            binding.recycler.adapter= NoteRecyclerAdapter(viewModel, it, this)
        })
    }

    fun addData(){

        var title=binding.titletxt.text.toString()
        if(title.isNullOrBlank()){
            Toast.makeText(this,"Enter value!", Toast.LENGTH_LONG).show()
        }else{
            var blog= Blog(title)
            viewModel.add(blog)
            binding.titletxt.text.clear()
            binding.recycler.adapter?.notifyDataSetChanged()
        }

    }
}