package lesson1

import java.lang.Exception

object Console {
    val levelText = "Choose difficulty: 1-Easy, 2-Medium, 3-Hard."
    val enterText = "Guess a number between 1 and 100"
    var guessNumber: Int = -1
    var difficultyNumber: Int = -1

    fun readDifficultyNumber(): Int {
        var text: String?

        try {
            text = readLine()
            difficultyNumber = text!!.toInt()
            if (difficultyNumber < 1 || difficultyNumber > 3) {
                throw Exception()
            }
            if (text!!.toDouble() - difficultyNumber == 0.0)
                return difficultyNumber
            throw Exception();

        } catch (e: Exception) {
            text = null
            println(levelText)
            println("Choose right number: 1,2 or 3")
            readDifficultyNumber()

        }
        return difficultyNumber
    }


    fun readGuessedNumber(): Int {
        var text: String?

        try {
            text = readLine()
            guessNumber = text!!.toInt()
            if (guessNumber < 0 || guessNumber > 100) {
                throw Exception()
            }
            if ((text!!.toDouble() - guessNumber) == 0.0)
                return guessNumber
            throw Exception();

        } catch (e: Exception) {
            text = null;
            println("Type right format: only number between 1-100")
            readGuessedNumber()

        }
        return guessNumber
    }
}





