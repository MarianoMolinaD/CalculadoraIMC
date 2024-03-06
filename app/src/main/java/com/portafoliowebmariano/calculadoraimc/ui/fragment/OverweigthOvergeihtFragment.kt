package com.portafoliowebmariano.calculadoraimc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.portafoliowebmariano.calculadoraimc.R
import com.portafoliowebmariano.calculadoraimc.databinding.FragmentOverweigthOvergeihtBinding
import com.portafoliowebmariano.calculadoraimc.util.StatusBarUtili


class OverweigthOvergeihtFragment : Fragment() {

    lateinit var binding: FragmentOverweigthOvergeihtBinding

    private val args: OverweigthOvergeihtFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentOverweigthOvergeihtBinding.inflate(inflater, container , false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        contoller()
    }

    private fun contoller() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(OverweigthOvergeihtFragmentDirections.actionOverweigthOvergeihtFragmentToHomeFragment())
        }
    }

    private fun initUI() {
        val result = args.bodyMass

        binding.tvResult.text = result.toString()

        StatusBarUtili.changeColor(this,R.color.colorGradientSobrepeso)
        isMenOrWoman()
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

}