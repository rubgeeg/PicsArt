package com.androidcamp.customprogressbar


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fillL.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    // calculating percentile of progressbar on screen touch
                    val xTouch: Float = event.x
                    val point = IntArray(2)
                    val width = v!!.width
                    v.getLocationOnScreen(point)
                    //  val xView = point[0]

                    fillL.setProgress((xTouch / width * 100).toInt())
                    fillL.setProgressColors(
                        intArrayOf(
                            R.color.colorGradient1,
                            R.color.colorGradient2
                        )
                    )
                }

            }

            v?.onTouchEvent(event) ?: true
        }

    }


}

