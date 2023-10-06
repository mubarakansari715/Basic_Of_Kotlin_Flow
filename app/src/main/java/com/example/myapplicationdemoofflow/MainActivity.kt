package com.example.myapplicationdemoofflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*
* Here, we create a shared flow. It's a similar flow,
*  but the main difference is that it doesn't provide data that
* has already been consumed.*/

        GlobalScope.launch(Dispatchers.Main) {

        }
    }
}

