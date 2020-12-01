package com.example.mylibrary2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MathOps(private val activity: Activity,
              private val onResultCallback : (String, String, String, String) -> Unit
): MathOpsResult {

    companion object{
        const val INPUT_ONE = "one"
        const val INPUT_TWO = "two"
        const val OPERATOR = "operator"
        const val RESULT_CODE = 104
    }

    override fun callActivity(inputOne: String, inputTwo: String, operator: String) {
        val intent = activity.packageManager.getLaunchIntentForPackage("com.example.myapplicationtwo")
        intent?.setFlags(0)
        intent?.putExtra(INPUT_ONE, inputOne)
        intent?.putExtra(INPUT_TWO, inputTwo)
        intent?.putExtra(OPERATOR, operator)
        activity.startActivityForResult(intent, RESULT_CODE)
    }
    
    override fun onResult(result: String, operation: String, inputOne: String, inputTwo: String) {
        onResultCallback(result, operation, inputOne, inputTwo)
    }
    
}

interface MathOpsResult{
    fun callActivity(inputOne : String, inputTwo : String, operator: String)

    fun onResult(result: String, operation: String, inputOne: String, inputTwo: String)
}