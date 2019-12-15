package com.androidcamp.minigame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.androidcamp.minigame.databinding.GameFragmentLayoutBinding


class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: GameFragmentLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_fragment_layout, container, false
        )



        binding.guessButton.visibility = View.GONE

        binding.editGuessNumber.setOnClickListener {
            if (!binding.editGuessNumber.text.toString().isNullOrEmpty())

                binding.guessButton.visibility = View.VISIBLE
        }


        binding.guessButton.setOnClickListener { view: View ->
            val guessedNumber = binding.editGuessNumber.text.toString()
            Player.player.score = guessedNumber
            val rnds = (0..3).random()


            if (rnds == guessedNumber.toInt()) {

                view.findNavController().navigate(R.id.action_gameFragment2_to_winFragment)
            } else {
                view.findNavController().navigate((R.id.action_gameFragment2_to_gameOverFragment))
            }

        }



        return binding.root


    }
}