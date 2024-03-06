package com.portafoliowebmariano.calculadoraimc.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.portafoliowebmariano.calculadoraimc.R
import com.portafoliowebmariano.calculadoraimc.databinding.FragmentLowWeightBinding
import com.portafoliowebmariano.calculadoraimc.util.StatusBarUtili

class LowWeightFragment : Fragment() {

    private lateinit var binding: FragmentLowWeightBinding

    private val args: LowWeightFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLowWeightBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        controller()
    }

    private fun isMenOrWoman() {
        val menOrWoman : Boolean = args.gender
        if (menOrWoman){
            binding.ivGender.setImageResource(R.drawable.men)
        }
        else{
            binding.ivGender.setImageResource(R.drawable.woman)
        }
    }

    private fun initUI() {
        val result = args.bodyMass
        binding.tvResult.text = result.toString()
        StatusBarUtili.changeColor(this, R.color.colorGradientLow)
        isMenOrWoman()
    }

    private fun controller() {
        binding.buttonBack.setOnClickListener {
            Log.i("Mitag", "Click Back")
            findNavController().navigate(LowWeightFragmentDirections.actionLowWeightFragmentToHomeFragment())
        }
    }
}