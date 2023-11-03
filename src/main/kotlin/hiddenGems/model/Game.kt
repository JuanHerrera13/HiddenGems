package hiddenGems.model

data class Game(val title: String, val thumb: String) {

    var description: String? = null

    override fun toString(): String {
        return "\nGame '$title'\nThumb = '$thumb'\nDescription = '$description'"
    }
}