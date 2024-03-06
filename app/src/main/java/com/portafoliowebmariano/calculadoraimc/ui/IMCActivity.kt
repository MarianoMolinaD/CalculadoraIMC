package com.portafoliowebmariano.calculadoraimc.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.portafoliowebmariano.calculadoraimc.databinding.ActivityImcBinding

class IMCActivity : AppCompatActivity() {

    private lateinit var binding : ActivityImcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}