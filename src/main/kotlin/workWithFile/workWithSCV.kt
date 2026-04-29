package workWithFile

import com.opencsv.CSVReader
import java.io.FileReader
import PrintConsole.*
import com.opencsv.CSVWriter
import java.io.FileWriter

fun loadFromCSV(path: String, error: error): UniversatyData? {
    return try {
    val reader = CSVReader(FileReader(path))
    val rows = reader.readAll()

    val studentsMap = mutableMapOf<Int, MutableList<Achievment>>()
    val studentInfo = mutableMapOf<Int, Student>()

    for (i in 1 until rows.size) { // пропускаем заголовок
        val row = rows[i]

        val id = row[0].toInt()
        val name = row[1]
        val surname = row[2]
        val faculty = row[3]
        val sport = row[4]
        val result = row[5]
        val date = row[6]

        val achievement = Achievment(sport, result, date)

        studentsMap.getOrPut(id) { mutableListOf() }.add(achievement)

        studentInfo[id] = Student(
            id,
            name,
            surname,
            faculty,
            studentsMap[id]!!
        )
    }

    reader.close()

    UniversatyData(studentInfo.values.toMutableList())
    } catch (e: Exception) {
        error.errorCode = 5
        null
    }
}

fun saveToCSV(path: String, data: UniversatyData?, error: error): Boolean {
    if(data == null){
        error.errorCode = 3
        return false
    }

    val writer = CSVWriter(FileWriter(path))

    writer.writeNext(arrayOf("id","name","surname","faculty","sport","result","date"))

    for (student in data.students) {
        if (student.achievements.isEmpty()) {
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
            for (ach in student.achievements) {
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
