package com.example.myapplicationone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mylibrary2.MathOps
import com.example.mylibrary2.MathOpsResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val ADDITION = "addition"
        const val SUBTRACTION = "subtraction"
        const val INPUT_ONE = "one"
        const val INPUT_TWO = "two"
        const val RESULT = "result"
        const val OPERATOR = "operator"
        const val RESULT_CODE = 104
    }

    private lateinit var  mathops : MathOpsResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mathops = MathOps(this){firstInput, secondInput, operator, result ->

            txt_input_one.setText(resources.getString(R.string.input_one)+" "+firstInput)
            txt_input_two.setText(resources.getString(R.string.input_two)+" "+secondInput)
            txt_input_operation.text = resources.getString(R.string.action)+" "+operator
            txt_input_result.setText(resources.getString(R.string.output)+" "+result)
        }

        btn_addition.setOnClickListener{
            val input1 = et_first_input.text.toString()
            val input2 = et_second_input.text.toString()

            mathops.callActivity(input1, input2, ADDITION)
        }

        btn_subtraction.setOnClickListener{
            val input1 = et_first_input.text.toString()
            val input2 = et_second_input.text.toString()

            mathops.callActivity(input1, input2, SUBTRACTION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CODE){
            mathops.onResult(""+data?.getStringExtra(INPUT_ONE),
                ""+data?.getStringExtra(INPUT_TWO),
                ""+data?.getStringExtra(OPERATOR)
            , ""+data?.getStringExtra(RESULT))
        }
    }


}