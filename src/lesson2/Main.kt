package lesson2

private const val welcomeText: String =
    "Please, enter username or hashtag \nNote: hashtags starts with '#' and username start with '@'"
private const val userNameSelect: String = "Choose username from list"
private const val hashtagSelect: String = "Choose hashtag from list"
private const val errorText: String = "Please type right command... "

fun readConsole(): String {
    var text: String? = readLine()
    if (text.isNullOrEmpty()) {
        println(errorText)
        return readConsole()
    }

    return text ?: readConsole()
}

fun main() {
    println(welcomeText)
    val enteredText = readConsole()
    when (enteredText.isNotEmpty()) {
        enteredText.startsWith("#") -> searchByHashtag(enteredText)
        enteredText.startsWith("@") -> searchByUsername(enteredText)
        else -> main()
    }


}

fun searchByHashtag(startText: String) {
    val tagSuggestionController: SuggestionController<Tag> = TagSuggestionController()

    val searchedTags = tagSuggestionController.search(startText)
    if (searchedTags.isEmpty()) {
        println("no tags found")
        return
    }
    println("searched tags: $searchedTags") // matched tags that contain 'a'
    println(hashtagSelect)
    val selectedTags = tagSuggestionController.select(readConsole())
    println("Selected tag: $selectedTags")
    val recentTags = tagSuggestionController.recent()
    println("recent tags: $recentTags") // recently added tags
}

fun searchByUsername(startText: String) {
    val userSuggestionController: SuggestionController<User> = UserSuggestionController()

    val searchedUsers = userSuggestionController.search(startText)
    if (searchedUsers.isEmpty()) {
        println("No user found")
        return
    }
    println("searched users: $searchedUsers")
    println(userNameSelect)
    val selectedUser = userSuggestionController.select(readConsole())
    println("Selected user: $selectedUser")
    val recentUsers = userSuggestionController.recent()
    println("Recent users: $recentUsers") // recently added users
}