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

        /*
        * Here, we create a shared flow. It's a similar flow,
        *  but the main difference is that it doesn't provide data that
        * has already been consumed.
        * --> reply data state second consumer with this index*/

        GlobalScope.launch(Dispatchers.Main) {

            val result = sharedFlowConsumer()
            result.collect {
                Log.e(TAG, "onCreate: First Consumer :: $it")
            }
        }

        GlobalScope.launch(Dispatchers.Main) {

            val result = sharedFlowConsumer()
            delay(2500)
            result.collect {
                Log.e(TAG, "onCreate: Second Consumer :: $it")
            }
        }
    }
}

//shared flow
fun sharedFlowConsumer(): Flow<Int> {
    //reply store last element data
    val mutableSharedFlow = MutableSharedFlow<Int>(1)
    GlobalScope.launch {
        val list = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            mutableSharedFlow.emit(it)
            delay(1000)
        }
    }
    return mutableSharedFlow
}

