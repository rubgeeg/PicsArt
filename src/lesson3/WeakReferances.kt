package lesson3

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class DelegateWeekRefProp<T>(val info: () -> T) {

    private var value: WeakReference<T> = WeakReference(info())
    operator fun getValue(ref: Any?, property: KProperty<*>): T =
        value.get() ?: info().also { value = WeakReference(it) }

}

private fun <T> weak(info: () -> T) = DelegateWeekRefProp(info)

fun main() {
    val value: Int by weak { 50 }
    println(value)
}