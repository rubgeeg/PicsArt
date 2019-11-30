package lesson4


class Node(val name: String) {
    val node = mutableListOf<Node>()

    fun node(numberNode: String, init: Node.() -> Unit) {
        val mNode = Node(numberNode)
        mNode.init()
        node.add(mNode)
    }

}

fun root(init: Node.() -> Unit) = Node("root").apply { init() }

fun main(args: Array<String>) {
    root {
        node("1") {
            node("3") {
                node("5") {}
            }
            node("4") {}
        }
        node("2") {}
    }
}

