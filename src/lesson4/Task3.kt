package lesson4

import  lesson4.GetType.*

fun main() {
    var arr = IntArray(0)
    arr = arr add 3 // create new array and add element at the end
    arr = arr add 7 add 1
    arr = arr add 9 add 6 add 8
    arr = arr insert 5 at 2 // insert 5 into position 2
    arr = arr `remove at` 3 // set to 0 at position 3
    arr = arr `remove at` 1


    // optional
    println()
    println(arr get size) // 6
    arr print all // 3 0 5 0 6 8

}

private infix fun IntArray.`remove at`(position: Int): IntArray {
    this[position] = 0
    return this
}

private infix fun IntArray.add(number: Int): IntArray {
    val size = this.size + 1
    var intArray = IntArray(size)
    forEachIndexed() { index, element ->
        intArray[index] = element
    }
    intArray[size - 1] = number
    return intArray
}

private infix fun IntArray.get(parameter: GetType): Int {
    when (parameter) {
        GetType.size -> return this.size
    }
    throw NotImplementedError()
}

private infix fun IntArray.print(parameter: GetType): Unit {
    when (parameter) {
        GetType.all ->
            for (item in this)
                print(item)
    }
}

class Insertion(private val array: IntArray, private val value: Int) {
    infix fun at(index: Int): IntArray {
        array[index] = value
        return array
    }
}

infix fun IntArray.insert(value: Int) = Insertion(this, value)

