package com.example.myapplicationdemoofflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .onStart {
                    Log.e(TAG, "onCreate: Consumer start initially", )
                }
                .map {
                    //make some changing on data. like here we make name is uppercase
                    Note(it.isActive, it.title.uppercase())
                }
                .filter {
                    //here make some filter on data
                    it.isActive
                }
                .collect {
                    //here only data is show, which one is active state
                    Log.e(TAG, "onCreate: $it")
                }
        }
    }
}

fun producer(): Flow<Note> {
    return flow {

        val list = arrayListOf<Note>(
            Note(true,"mubarak"),
            Note(true,"ansari"),
            Note(false,"happy"),
        )

        list.forEach {
            emit(it)
            delay(1000)
        }
    }
}

data class Note(
    val isActive: Boolean,
    val title: String
)