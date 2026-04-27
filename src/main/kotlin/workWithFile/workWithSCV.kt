package workWithFile

import com.opencsv.CSVReader
import java.io.FileReader

fun loadFromCSV(path: String): UniversatyData {
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

    return UniversatyData(studentInfo.values.toMutableList())
}

