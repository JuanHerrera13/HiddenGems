package hiddenGems.model

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

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', id=$id, birthDate=$birthDate, userName=$userName)"
    }
}