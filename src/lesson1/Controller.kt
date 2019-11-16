package lesson1

import lesson1.Console.readGuessedNumber

var difficulty = Level.EASY
var attempts = 0

fun isLucky(guessNumber: Int, randomNumber: Int): Boolean {
    if (guessNumber == randomNumber) {
        println("Congratulation you won!!!")
        return false
    }
    if (guessNumber > randomNumber) {
        println("Take lower")
        return true
    }
    if (guessNumber < randomNumber) {
        println("Take higher")
        return true
    }
    return true

}

fun setDifficulty(num: Int) {

    if (num == 1)
        return
    if (num == 2)
        difficulty = Level.MEDIUM
    if (num == 3)
        difficulty = Level.HARD
}


fun startGame() {
    val generatedNum = getRandomNumber()
    var isGuessed = true
    println(Console.levelText)

    while (isGuessed) {
        val number = Console.readDifficultyNumber()
        setDifficulty(number)
        //   println("difficulty lvl $difficulty")
        attempts = when (difficulty) {
            Level.HARD -> 3
            Level.MEDIUM -> 5
            Level.EASY -> 7
        }
        println("attempts: $attempts")
        while (attempts > 0) {
            val guessNumber = readGuessedNumber()
            isGuessed = isLucky(guessNumber, generatedNum)
            if (!isGuessed) return
            attempts--
            if (attempts < 1) {
                println("YOU LOSE!!!")
                isGuessed = false
            }

        }
    }
    if (!isGuessed) {
        startGame()
    }


}
fun getRandomNumber(): Int {
    return (0..100).random()
}

