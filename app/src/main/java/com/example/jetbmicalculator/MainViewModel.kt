package com.example.jetbmicalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import kotlin.math.roundToInt

class MainViewModel : ViewModel() {
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var bmi by mutableStateOf(0f)
    var judgedColor by mutableStateOf(Color.Gray)

    fun calculateBMI() {
        val heightNum = height.toFloatOrNull()?.div(100) ?: 0f
        val weightNum = weight.toFloatOrNull() ?: 0f

        if (heightNum < 0f || weightNum < 0f) {
            bmi = 0f
            judgedColor = Color.Gray
            return
        }

        bmi = (weightNum / heightNum.pow(2) * 10).roundToInt() / 10f

        // BMIによって色を変える
        if (bmi > 25) {
            judgedColor = Color.Red
            return
        }

        if (bmi < 18.5) {
            judgedColor = Color.Blue
            return
        }

        judgedColor = Color.Gray
    }
}