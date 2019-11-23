package lesson2

data class User(
    val id: String,
    val userName: String,
    val name: String,
    val surname: String
) {
    companion object {
        var Empty = User("", "", "", "")
    }
}

data class Tag(
    val id: String,
    val name: String
) {
    companion object {
        var Empty = Tag("", "")
    }
}