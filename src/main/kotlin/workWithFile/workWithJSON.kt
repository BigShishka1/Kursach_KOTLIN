package workWithFile

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import PrintConsole.formedDATA

@Serializable
data class Achievment(
    val sport: String,
    val result: String,
    val date: String
)

@Serializable
data class Student(
    val idStudent: Int,
    val nameStudent: String,
    val secNameStudent: String,
    val faculityStudent: String,
    val achievements: List<Achievment>
)

@Serializable
data class UniversatyData(
    val students: MutableList<Student>
)

fun loadDataFromJson(fileName: String): UniversatyData {
    val jsonString = File(fileName).readText(Charsets.UTF_8)
    formedDATA()
    return Json.decodeFromString(jsonString)
}