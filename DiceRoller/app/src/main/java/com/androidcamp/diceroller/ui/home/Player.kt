package com.androidcamp.diceroller.ui.home


data class Player(var name: String) {
    var score = 0

    companion object {
        val playerList = mutableListOf<Player>()
        const val WIN_SCORE = 20

        fun setPlayerCount(count: Int) {
            for (i in 1..count) {
                playerList.add(Player("Player $i"))
            }
        }

        fun reset(){
            playerList.forEach(){
                it.score = 0
            }
        }

    }
}
