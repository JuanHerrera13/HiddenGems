data class Game(val title: String, val thumb: String) {

    val description = ""

    override fun toString(): String {
        return "Game '$title'\nThumb = '$thumb'\nDescription = '$description'"
    }
}