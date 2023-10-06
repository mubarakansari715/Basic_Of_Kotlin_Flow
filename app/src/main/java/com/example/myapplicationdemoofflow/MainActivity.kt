package com.example.myapplicationdemoofflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .collect {
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
            Note(true,"happy"),
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