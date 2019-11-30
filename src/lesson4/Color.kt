package lesson4


object Color {
    var alpha = -1
    var red = -1
    var green = -1
    var blue = -1


    private inline fun Int.toColor(): Color {
        alpha = (this and 0xffffff) shr 24
        red = (this and 0xff0000) shr 16
        green = (this and 0x00ff00) shr 8
        blue = (this and 0x0000ff) shr 0
        return Color
    }

    fun test() {
        val c = (-0x775FB34F).toColor()
        println(c) // Color(a=136, r=160, g=76, b=177)
    }

    override fun toString(): String {
        return "Color(a=$alpha, r=$red, g=$green, b=$blue)"
    }

}