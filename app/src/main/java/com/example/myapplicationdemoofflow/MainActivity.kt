package com.example.myapplicationdemoofflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * Here emit data via stateflow
        * Stateflow save last data in Box
        * when any other consumer call it's rerun last data of box
        * */

        GlobalScope.launch {
            val result = stateFlowConsumer()

            result.collect {
                Log.e(TAG, "onCreate: state flow :: $it")
            }
        }
        
        /*
        * If you want value of state flow
        * then state flow give value using `value` key*/
        GlobalScope.launch { 
            val result = stateFlowValueConsumer()
            Log.e(TAG, "onCreate: stateFlowValueConsumer :: ${result.value}", )
        }
    }
}

//state flow
fun stateFlowConsumer(): Flow<Int> {
    //initially stateflow value is 10
    val mutableStateFlow = MutableStateFlow(10)

    GlobalScope.launch {
        delay(2000)
        mutableStateFlow.emit(20)

        delay(2000)
        mutableStateFlow.emit(30)

        delay(2000)
        mutableStateFlow.emit(40)
    }

    return mutableStateFlow
}

//state flow value
fun stateFlowValueConsumer(): StateFlow<Int> {
    //initially stateflow value is 10
    val mutableStateFlow = MutableStateFlow(10)

    return mutableStateFlow
}

