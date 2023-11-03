package hiddenGems.main

import hiddenGems.model.Game
import hiddenGems.model.Gamer
import hiddenGems.service.ExternalSharkApi
import transformIntoAge
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val gamer = Gamer.createGamer(scanner)
    println("Registry finished successfully!\n")
    println(gamer)
    println("Gamer's age is ${gamer.birthDate?.transformIntoAge()}")

    do {
    print("\nType the game code to be searched: ")
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
            gamer.searchedGames.add(myGame)
        }

        println("\nDo you want to search another game? (Y/N)")
        var answer = scanner.nextLine()

    } while (answer.equals("Y", true))

    println("Searched games:")
    println(gamer.searchedGames)

    println("\nSearched Games sorted by title: ")
    gamer.searchedGames.sortBy {
        it?.title
    }
    gamer.searchedGames.forEach() {
        println("Title: " + it?.title)
    }

    val filteredGames = gamer.searchedGames.filter {
        it?.title?.contains("Batman", true) ?: false
    }

    println("\nFiltered Games: ")
    println(filteredGames)

    println("Do you want to remove any game from the original list? (Y/N)")
    val answer = scanner.nextLine()

    if (answer.equals("Y", true)) {
        println(gamer.searchedGames)
        println("\nType the game's number position to remove it: ")
        val gamePosition = scanner.nextInt()
        gamer.searchedGames.removeAt(gamePosition)
    }

    println("\nUpdated list: ")
    println(gamer.searchedGames)

    println("\nSearch completed successfully!")
}