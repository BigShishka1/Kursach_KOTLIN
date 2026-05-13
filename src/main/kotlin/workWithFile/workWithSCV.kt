package workWithFile

import com.opencsv.CSVReader
import java.io.FileReader
import PrintConsole.*
import com.opencsv.CSVWriter
import java.io.FileWriter

//Загрузка данных из CSV файл (Имя файла CSV, Объект ошибки)
fun loadFromCSV(path: String, error: error): UniversatyData? {
    return try {
    val reader = CSVReader(FileReader(path)) //Чтение файла
    val rows = reader.readAll() //Деление на строки

    //Коллекция, которая хранит в себе достижения по ID
    val studentsMap = mutableMapOf<Int, MutableList<Achievment>>()
    //Коллекция, которая хранит в себе данные о студентах
    val studentInfo = mutableMapOf<Int, Student>()

    for (i in 1 until rows.size) { // пропускаем заголовок
        val row = rows[i]
        //Парсим значения из строки
        val id = row[0].toInt()
        val name = row[1]
        val surname = row[2]
        val faculty = row[3]
        val sport = row[4]
        val result = row[5]
        val date = row[6]

        val achievement = Achievment(sport, result, date)

        studentsMap.getOrPut(id) { mutableListOf() }.add(achievement)

        //Формируем запись о студенте
        studentInfo[id] = Student(
            id,
            name,
            surname,
            faculty,
            studentsMap[id]!!
        )
    }

    reader.close()
    //Создаём данные
    UniversatyData(studentInfo.values.toMutableList())
    } catch (e: Exception) { //Выводим ошибку если возникли проблемы при роботе с файлом
        error.errorCode = 5
        null
    }
}

//Сохраняет имеющееся данные в CSV файл (Название файла для сохранения, Данные, Объект ошибки)
fun saveToCSV(path: String, data: UniversatyData?, error: error): Boolean {
    if(data == null){ //Проверка на существование данных
        error.errorCode = 3
        return false
    }
    //Запись файла
    val writer = CSVWriter(FileWriter(path))

    //Записываем заголовки таблицы
    writer.writeNext(arrayOf("id","name","surname","faculty","sport","result","date"))

    //Записываем в файл значения из данных
    for (student in data.students) {
        if (student.achievements.isEmpty()) { //Если достижений нет
            writer.writeNext(arrayOf(
                student.idStudent.toString(),
                student.nameStudent,
                student.secNameStudent,
                student.faculityStudent,
                "",
                "",
                ""
            ))
        } else {
            for (ach in student.achievements) { //Если достижения есть
                writer.writeNext(arrayOf(
                    student.idStudent.toString(),
                    student.nameStudent,
                    student.secNameStudent,
                    student.faculityStudent,
                    ach.sport,
                    ach.result,
                    ach.date
                ))
            }
        }
    }

    writer.close()
    return true
}
