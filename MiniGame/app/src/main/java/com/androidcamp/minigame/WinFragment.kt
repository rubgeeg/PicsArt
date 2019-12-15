package com.androidcamp.minigame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.androidcamp.minigame.databinding.WinFragmentLayoutBinding

class WinFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: WinFragmentLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.win_fragment_layout, container, false
        )
        binding.player = Player.player

        binding.winNewGame.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_winFragment_to_gameFragment2)
        }

        binding.shareButton.setOnClickListener { view: View ->
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = Player.player.score
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here")
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))


        }




        return binding.root
    }


}