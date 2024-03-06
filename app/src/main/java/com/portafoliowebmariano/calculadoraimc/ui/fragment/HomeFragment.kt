package com.portafoliowebmariano.calculadoraimc.ui.fragment

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.portafoliowebmariano.calculadoraimc.R
import com.portafoliowebmariano.calculadoraimc.databinding.FragmentHomeBinding
import com.portafoliowebmariano.calculadoraimc.util.StatusBarUtili
import com.portafoliowebmariano.calculadoraimc.viewmodel.ImcViewModel
import java.text.DecimalFormat


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var audioClick: MediaPlayer

    private val imcViewModel: ImcViewModel by viewModels()
    private var isMenOrWomen = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        controller()
        observer()
        medioController()

    }

    private fun medioController() {
        audioClick = MediaPlayer.create(context, R.raw.click)
    }

    private fun initUI() {
        StatusBarUtili.changeColor(this, R.color.gradientOne)
    }

    private fun observer() {
        imcViewModel.bodyMass.observe(viewLifecycleOwner) { bodyMas ->
            calBodyMass(bodyMas)
        }
    }

    private fun controller() {
        //Click Boton Calcular
        binding.btnCalcular.setOnClickListener {
            val weight = binding.tvWeight.text.toString().toFloat()
            val height = binding.tvHeight.text.toString().toFloat()

            imcViewModel.getBodyMass(weight, height)

        }

        //slider RangeSlider
        binding.sbAltura.addOnChangeListener { _, value, _ ->
            var df = DecimalFormat("#.##")
            val resultado = df.format(value)
            binding.tvHeight.text = resultado
        }

        //Boton sumar
        binding.btnAdd.setOnClickListener {
            audioClick.start()
            val weight = binding.tvWeight.text.toString()
            val weightAdd = weight.toInt() + 1
            binding.tvWeight.text = weightAdd.toString()
        }
        //Boton restar
        binding.btnSubtract.setOnClickListener {
            audioClick.start()
            val weight = binding.tvWeight.text.toString()
            val weightSustract = weight.toInt() - 1
            binding.tvWeight.text = weightSustract.toString()
        }

        //Boton Men
        binding.btnMen.setOnClickListener {
            binding.btnMen.setBackgroundResource(R.drawable.gender_selected)
            binding.btnWoman.setBackgroundResource(R.color.white)
            isMenOrWomen = true

        }
        binding.btnWoman.setOnClickListener {
            binding.btnWoman.setBackgroundResource(R.drawable.gender_selected)
            binding.btnMen.setBackgroundResource(R.color.white)
            isMenOrWomen = false
        }
    }

    private fun calBodyMass(bodyMass: Float) {
        when (bodyMass) {
            in 0.00..18.50 -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLowWeightFragment(
                        bodyMass,
                        isMenOrWomen
                    )
                )
            }

            in 18.51..24.99 -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToNormalWeightFragment(
                        bodyMass,
                        isMenOrWomen
                    )
                )
            }

            in 25.00..29.99 -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToOverweigthOvergeihtFragment(
                        bodyMass,
                        isMenOrWomen
                    )
                )
            }

            in 30.00..100.99 -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToObesityWeightFragment(
                        bodyMass,
                        isMenOrWomen
                    )
                )
            }

            return -> {
                ""
            }
        }
    }
}