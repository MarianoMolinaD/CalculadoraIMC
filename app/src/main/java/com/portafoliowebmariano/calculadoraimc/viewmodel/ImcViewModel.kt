package com.portafoliowebmariano.calculadoraimc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Formatter

class ImcViewModel : ViewModel() {

    var bodyMass = MutableLiveData<Float>()

    fun getBodyMass(weight: Float, height: Float) {
        val heightMts = height / 100
        val heightMts2 = heightMts * heightMts
        val result = weight / heightMts2
        Log.i("Mitag",formater(result))
        bodyMass.postValue(formater(result).toFloat())
    }

    fun formater(numero: Float): String {
        val formato = Formatter()
        formato.format("%.2f", numero)
        return formato.toString()
    }
}
