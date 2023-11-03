package hiddenGems.main

import hiddenGems.model.Gamer

fun main() {
    val gamer1 = Gamer("pete", "pete@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Mary", "mj@gmail.com", "28/04/2023", "mj")
    println(gamer2)

    gamer1.let {
        it.birthDate = "13/09/2002"
        it.userName = "pete"
    }.also {
        println(gamer1.id)
    }

    println(gamer1)
    gamer1.userName = "pparker"
    println(gamer1)
}