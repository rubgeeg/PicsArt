package lesson2

interface SuggestionController<T> {
    var recentList: MutableSet<String>
    fun search(query: String): List<String>
    fun recent(): MutableSet<String>
    fun isValid(query: String): Boolean
    fun select(query: String): T
}

class UserSuggestionController : SuggestionController<User> {
    private val PREFIX = '@'
    override var recentList: MutableSet<String> = mutableSetOf()

    override fun search(query: String): List<String> {
        val searchList = mutableListOf<String>()
        if (isValid(query)) {

            for (item in userList) {
                if (item.name.contains(query.subSequence(1, query.length)) || item.surname.contains(query.subSequence(1, query.length)))
                    searchList.add(item.userName)
            }
        }
        return searchList

    }

    override fun recent(): MutableSet<String> = recentList


    override fun select(query: String): User {

        userList.forEach {
            if (it.userName == query) {
                recentList.add(query)
                return it
            }

        }

        return User.Empty
    }

    override fun isValid(query: String): Boolean = query.isNotEmpty() && query.startsWith(PREFIX)

}

class TagSuggestionController : SuggestionController<Tag> {
    private val TAG_PREFIX = '#'
    override var recentList: MutableSet<String> = mutableSetOf()

    override fun search(query: String): List<String> {
        val searchList = mutableListOf<String>()
        if (isValid(query)) {

            tagList.forEach {
                if (it.name.contains(query.substring(1, query.length)) ) {
                    searchList.add(it.name)
                }
            }
        }
        return searchList
    }

    override fun recent(): MutableSet<String> = recentList

    override fun isValid(query: String): Boolean = query.isNotEmpty() && query.startsWith(TAG_PREFIX)


    override fun select(query: String): Tag {
        tagList.forEach {
            if (it.name == query) {
                recentList.add(it.name)
                return it
            }
        }
        return Tag.Empty
    }

}