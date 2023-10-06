package com.example.myapplicationdemoofflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* GlobalScope.launch {
             setNoteData()
                 .map {
                     Note(it.id, it.isActive, it.title.uppercase())
                 }
                 .filter {
                     it.isActive
                 }
                 .collect {

                     Log.e("TAG", "onCreate Map: $it")

                     val toGson = Gson().toJson(it)
                     Log.e("TAG", "onCreate: $toGson")

                     val fromGson = Gson().fromJson<Note>(toGson,Note::class.java)
                     Log.e("TAG", "onCreate: fromGson :: $fromGson", )

                     for(i in 1..1000){
                         Log.e("TAG", "onCreate: $i", )
                     }

                 }
         }*/

        //normal flow
        /* GlobalScope.launch(Dispatchers.Main) {
             val result = producer()
                 .collect {
                     Log.e(TAG, "onCreate: $it ")
                 }
         }*/

        //shared flow
        GlobalScope.launch(Dispatchers.Main) {
            val result = sharedFlow()
            result.collect {
                Log.e(TAG, "onCreate: item 1 :: $it")
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val result = sharedFlow()
            delay(3000)
            result.collect {
                Log.e(TAG, "onCreate: item 2 :: $it")
            }
        }

    }
}

//shared flow
fun sharedFlow(): Flow<Int> {
    val mutableSharedFlow = MutableSharedFlow<Int>()
    GlobalScope.launch {
        val list = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            mutableSharedFlow.emit(it)
            delay(1000)
        }
    }
    return mutableSharedFlow
}
//normal flow
/*private fun producer(): Flow<Int> {
    return flow {
        val list = arrayListOf<Int>(1, 2, 3, 4, 5)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}*/
/*
fun setNoteData(): Flow<Note> {
    val list = arrayListOf<Note>(
        Note(1, true, "mubarak"),
        Note(2, true, "happy"),
        Note(3, false, "yo"),
    )
    return list.asFlow()
}

data class Note(
    val id: Int,
    val isActive: Boolean,
    val title: String
)*/
