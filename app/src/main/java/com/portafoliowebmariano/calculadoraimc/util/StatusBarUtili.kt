package com.portafoliowebmariano.calculadoraimc.util

import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import com.portafoliowebmariano.calculadoraimc.R

object StatusBarUtili {
     fun changeColor(fragment : Fragment, colorResId : Int) {
        // Cambiar el color de la barra de estado
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.activity?.window?.statusBarColor = fragment.resources.getColor(colorResId, fragment.requireContext().theme)
        }
    }
}