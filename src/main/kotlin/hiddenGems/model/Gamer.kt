package hiddenGems.model

import java.util.*
import kotlin.random.Random

data class Gamer(var name: String, var email: String) {

    var id: String? = null
        private set
    var birthDate: String? = null
    var userName: String? = null
        set(value) {
            field = value
            if (id.isNullOrBlank()) {
                generateId()
            }
        }
    val searchedGames = mutableListOf<Game?>()

    constructor(name: String, email: String, birthDate: String, userName: String) :
            this(name, email) {
        this.birthDate = birthDate
        this.userName = userName
        generateId()
    }

    init {
        validateName(name)
        this.email = validateEmail()
    }

    fun generateId() {
        val number = Random.nextInt()
        val tag = String.format("%04d", number)
        "$userName#$tag".also { this.id = it }
    }

    fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalAccessError("Invalid e-mail")
        }
    }

    fun validateName(name: String?) {
        if (name.isNullOrBlank()) {
            throw IllegalAccessError("The name is blank!")
        }
    }

    companion object {
        fun createGamer(scanner: Scanner): Gamer {
            println("- - - Welcome to HiddenGems - - - \nLets gonna make your registry!\nType your name: ")
            val name = scanner.nextLine()
            println("Type your e-mail:")
            val email = scanner.nextLine()
            println("Would you like to complete your registry with user and date of birth? (Y/N)")
            val answer = scanner.nextLine()
            if (answer.equals("Y", true)) {
                println("Type your day of birth(DD/MM/YYYY):")
                val birthDate = scanner.nextLine()
                println("Type your username:")
                val userName = scanner.nextLine()
                return Gamer(name, email, birthDate, userName)
            } else {
                return Gamer(name, email)
            }
        }
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', id=$id, birthDate=$birthDate, userName=$userName)"
    }
}