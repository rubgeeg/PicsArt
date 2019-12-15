package com.androidcamp.minigame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.androidcamp.minigame.databinding.RegistrationFragmentLayoutBinding

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.registration_fragment_layout.*


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: RegistrationFragmentLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.registration_fragment_layout, container, false
        )

        binding.startButton.setOnClickListener { view: View ->
            if (binding.editName.text.isNullOrEmpty() || binding.editSurname.text.isNullOrEmpty()) {
                Toast.makeText(this.context, "Write your name and surname", Toast.LENGTH_LONG)
                    .show()
            } else {
                val name = binding.editName.text.toString()
                val surname = binding.editSurname.text.toString()

                Player.player.name = name
                Player.player.surname = surname


                view.findNavController().navigate(R.id.action_registrationFragment_to_gameFragment)

            }


        }


        return binding.root


    }


}