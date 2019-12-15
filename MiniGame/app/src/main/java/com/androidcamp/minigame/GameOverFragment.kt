package com.androidcamp.minigame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.androidcamp.minigame.databinding.GameOverFragmentBinding


class GameOverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: GameOverFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_over_fragment, container, false
        )
        binding.player = Player.player


        binding.button2.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment2)
        }

        return binding.root
    }

}