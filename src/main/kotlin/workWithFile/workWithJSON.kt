package workWithFile

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.File
import PrintConsole.*

//Data class, который формирует спортивное достижение за счёт своих полей
@Serializable
data class Achievment(
    val sport: String, //Вид спорта
    val result: String, //Результат
    val date: String //Дата
)

//Data class, который формирует студента
@Serializable
data class Student(
    val idStudent: Int, //ID студента
    val nameStudent: String, //Имя студента
    val secNameStudent: String, //Фамилия
    val faculityStudent: String, //Факультет
    val achievements: List<Achievment> //Список содержащий экземпляры класса Achivment (Достижения)
)

//Data class, который формирует общие данные института
@Serializable
data class UniversatyData(
    val students: MutableList<Student> //Список содержащий экземпляры класса Student (Студентов)
)

//Загрузка данных из JSON файл (Имя файла JSON, Объект ошибки)
fun loadDataFromJson(fileName: String, error: error): UniversatyData? {
    return try {
        val jsonString = File(fileName).readText(Charsets.UTF_8) //Чтение файла JSON
        Json.decodeFromString<UniversatyData>(jsonString) //Преобразования полученной стоки в объект
    } catch (e: Exception) { //Если возникает ошибка с чтением код выводит ошибку
        error.errorCode = 5
        null
    }
}

//Сохраняет имеющееся данные в JSON файл (Название файла для сохранения, Данные, Объект ошибки)
fun saveToJson(path: String, data: UniversatyData?, error: error): Boolean {
    if (data == null){ //Проверка на существование данных
        error.errorCode = 3
        return false
    }

    val json = Json { //Включаем чтоб на выходе файл выглядел красиво
        prettyPrint = true
    }
    val jsonString = json.encodeToString(data) //Переводим данные в
    File(path).writeText(jsonString, Charsets.UTF_8) //Запись в файл
    return true
}