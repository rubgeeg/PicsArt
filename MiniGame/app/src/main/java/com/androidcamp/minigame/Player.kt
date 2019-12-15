package com.androidcamp.minigame

data class Player(var name: String = "", var surname: String = "") {
    var score = ""


    companion object{
        var player = Player("","")
    }
}