import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.transformIntoAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val age: Int
    val birthDate = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    age = Period.between(birthDate, today).years
    return age
}
