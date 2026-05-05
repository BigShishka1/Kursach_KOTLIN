package workWithFile

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.File
import PrintConsole.*

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

fun loadDataFromJson(fileName: String, error: error): UniversatyData? {
    return try {
        val jsonString = File(fileName).readText(Charsets.UTF_8)
        Json.decodeFromString<UniversatyData>(jsonString)
    } catch (e: Exception) {
        error.errorCode = 5
        null
    }
}

fun saveToJson(path: String, data: UniversatyData?, error: error): Boolean {

    if (data == null){
        error.errorCode = 3
        return false
    }

    val json = Json {
        prettyPrint = true
    }
    val jsonString = json.encodeToString(data)
    File(path).writeText(jsonString, Charsets.UTF_8)
    return true
}