package ru.fastly.clicker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.fastly.clicker.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private var leftSum = 0
    private var rightSum = 0
    private val LEFT_SIDE = "left"
    private val RIGHT_SIDE = "right"
    private lateinit var binding: ActivityMainBinding


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(LEFT_SIDE, leftSum)
        savedInstanceState.putInt(RIGHT_SIDE, rightSum)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)


        leftSum = savedInstanceState?.getInt(LEFT_SIDE,0) ?:0
        rightSum = savedInstanceState?.getInt(RIGHT_SIDE,0) ?:0


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leftMinusButton = binding.leftMinus
        val leftPlusButton = binding.leftPlus
        val rightMinusButton = binding.rightMinus
        val rightPlusButton = binding.rightPlus
        val leftCounterText = binding.leftCounter
        val rightCounterText = binding.rightCounter

        leftCounterText.text = leftSum.toString()
        rightCounterText.text = rightSum.toString()

        leftMinusButton.setOnClickListener {
           setClick(LEFT_SIDE,'-', leftCounterText)
        }

        //удержание кнопки трет счетчик
        leftMinusButton.setOnLongClickListener {
            clearCounter(LEFT_SIDE, leftCounterText)
        }

        leftPlusButton.setOnClickListener{
            setClick(LEFT_SIDE,'+', leftCounterText)
        }

        rightMinusButton.setOnClickListener {
            setClick(RIGHT_SIDE,'-', rightCounterText)
        }

        //удержание кнопки трет счетчик
        rightMinusButton.setOnLongClickListener{
            clearCounter(RIGHT_SIDE, rightCounterText)
        }

        rightPlusButton.setOnClickListener {
            setClick(RIGHT_SIDE,'+', rightCounterText)
        }

    }

    private fun clearCounter(side:String, view: TextView):Boolean {
        if (side == LEFT_SIDE) {
            leftSum = 0
            view.text = leftSum.toString()
        } else {
            rightSum = 0
            view.text = rightSum.toString()
        }
        return true
    }

    private fun setClick(side:String, action:Char, view: TextView){
        if (side == LEFT_SIDE) {
           when (action) {
               '-' -> if (leftSum > 0) leftSum--
               '+' -> leftSum++
               }
            view.text = leftSum.toString()
            }
         else {
            when (action) {
                '-' -> if (rightSum > 0)  rightSum--
                '+' -> rightSum++
            }
            view.text = rightSum.toString()
        }
    }

}

