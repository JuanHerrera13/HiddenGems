package hiddenGems.main

import hiddenGems.model.Game
import hiddenGems.service.ExternalSharkApi
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Type the game code to be searched: ")
    val search = scanner.nextLine()

    val fetchApi = ExternalSharkApi()
    val gameInfo = fetchApi.fetchGame(search)

    var myGame: Game? = null

    val result = runCatching {
        myGame = Game(
            gameInfo.info.title,
            gameInfo.info.thumb
        )
    }

    result.onFailure {
        println("Nonexistent game. Try other id.")
    }

    result.onSuccess {
        println("Would you like to add a customized description? Y/N")
        val option = scanner.nextLine()
        if (option.equals("y", true)) {
            println("Insert the customized description:")
            val customizedDescription = scanner.nextLine()
            myGame?.description = customizedDescription
        } else {
            myGame?.description = myGame?.title
        }
        println(myGame)
    }

    result.onSuccess {
        println("Search completed successfully!")
    }
}