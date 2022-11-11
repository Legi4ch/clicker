package ru.fastly.clicker

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.TextView
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor



class MainActivity : AppCompatActivity() {

    private var leftSum = 0
    private var rightSum = 0
    val PREF_KEY = "storage"
    val PREF_LEFT = "left"
    val PREF_RIGHT = "right"

    lateinit var appSetting:SharedPreferences


    fun saveCounters() {
     val editor:Editor =  appSetting.edit()
     editor.putInt(PREF_LEFT,leftSum)
     editor.putInt(PREF_RIGHT, rightSum)
     editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

        appSetting = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        leftSum = appSetting.getInt(PREF_LEFT, 0)
        rightSum = appSetting.getInt(PREF_RIGHT, 0)


        setContentView(R.layout.activity_main)

        val leftMinus = findViewById<Button>(R.id.leftMinus)
        val leftPlus = findViewById<Button>(R.id.leftPlus)
        val rightMinus = findViewById<Button>(R.id.rightMinus)
        val rightPlus = findViewById<Button>(R.id.rightPlus)
        val leftCounter = findViewById<TextView>(R.id.leftCounter)
        val rightCounter = findViewById<TextView>(R.id.rightCounter)

        leftCounter.text = leftSum.toString()
        rightCounter.text = rightSum.toString()

        leftMinus.setOnClickListener {
           setClick("left",'-', leftCounter)
        }

        //удержание кнопки трет счетчик
        leftMinus.setOnLongClickListener(View.OnLongClickListener {
            clearCounter("left", leftCounter)
        })

        leftPlus.setOnClickListener{
            setClick("left",'+', leftCounter)
        }

        rightMinus.setOnClickListener {
            setClick("right",'-', rightCounter)
        }

        //удержание кнопки трет счетчик
        rightMinus.setOnLongClickListener(View.OnLongClickListener {
            clearCounter("right", rightCounter)
        })

        rightPlus.setOnClickListener {
            setClick("right",'+', rightCounter)
        }

    }

    fun clearCounter(side:String, view: TextView):Boolean {
        if (side == "left") {
            leftSum = 0
            view.text = leftSum.toString()
        } else {
            rightSum = 0
            view.text = rightSum.toString()
        }
        saveCounters()
        return true
    }


    fun setClick(side:String, action:Char, view: TextView){
        if (side == "left") {
           when (action) {
               '-' -> if (leftSum < 1) 0 else leftSum--
               '+' -> leftSum++
               }
            view.text = leftSum.toString()
            }
         else {
            when (action) {
                '-' -> if (rightSum < 1) 0 else rightSum--
                '+' -> rightSum++
            }
            view.text = rightSum.toString()
        }
        saveCounters()
    }
}
