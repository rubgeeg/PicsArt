package com.androidcamp.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_first_task.*

class FirstTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task)

        checkButton.setOnClickListener{
            val text:String? = editText.text.toString()

            if(!text.isNullOrEmpty()){
                textView.text = text
                editText.setText("")
            }else{
                Toast.makeText(this,"Please, type text",Toast.LENGTH_LONG).show()
            }


        }
    }
}
