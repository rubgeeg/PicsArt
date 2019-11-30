package lesson4

fun main(args: Array<String>) {
    // Create forEachWord function
    "please print each word".forEachWord { word ->
        println(word)
    }

    // create toColor function
    val c = (-0x775FB34F).toColor()
    println(c) // Color(a=136, r=160, g=76, b=177)


    // create bitIsOneAtPosition
    print(4.bitIsOneAtPosition(3))

}

private inline fun String.forEachWord(function: (String) -> Unit) {
    this.forEach {
        if (it != ' ') {
            print(it)
        } else {
            println()
        }
    }

}


private inline fun Int.toColor(): Color {
    Color.alpha = (this and 0xffffff) shr 24
    Color.red = (this and 0xff0000) shr 16
    Color.green = (this and 0x00ff00) shr 8
    Color.blue = (this and 0x0000ff) shr 0
    return Color
}

private inline fun Int.bitIsOneAtPosition(position: Int): Char {
    val number = Integer.toBinaryString(this)
    return number[position - 1]
}
