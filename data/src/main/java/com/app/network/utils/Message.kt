package com.app.network.utils

import android.content.Context
import android.widget.Toast

object Message {

    fun showMessage(context: Context, message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(context: Context, message:Int){
//        Toast.makeText(context, "$message", Toast.LENGTH_SHORT).show()
    }

    fun showMessage(context: Context, message:Long){
//        Toast.makeText(context, "$message", Toast.LENGTH_SHORT).show()
    }


}