package workWithDATA
import workWithFile.*
import PrintConsole.*

fun addRecord(data: UniversatyData?, error: error, index: Int): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }

    if (index > 0 && findRecordByIndex(data, error, index) > 0) else return false

    val newID = readln().toIntOrNull() ?: -1
    if (newID == -1) {
        error.errorCode = 1
        return false
    }

    val newName = readln()
    val newSecName = readln()
    val newFaculty = readln()
    val newAchievments = readln().split(" ")
    var listAchievment = mutableListOf<Achievment>()
    for (i in newAchievments){
        val newResult = readln()
        val newDate = readln()
        val newAchievment: Achievment = Achievment(i, newResult, newDate)
        listAchievment.add(newAchievment)
    }
    var newStudent: Student = Student(newID, newName, newSecName, newFaculty, listAchievment)

    when(index){
        -1 -> data.students.add(newStudent)
        else -> {
            val realIndex = data.students.indexOfFirst { it.idStudent == index }

            if (realIndex == -1) {
                error.errorCode = 4
                return false
            }

            data.students[realIndex] = newStudent
        }
    }
    return true
}

fun delRecord(data: UniversatyData?, error: error): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }

    val id = readln().toIntOrNull()

    if (id == null)
    {
        error.errorCode = 1
        return false
    }

    val removed = data.students.removeIf { it.idStudent == id }

    if (removed) return true
    else {
        error.errorCode = 4
        return false
    }
}

private fun findRecordByIndex(data: UniversatyData?, error: error, index: Int): Int{
    if (data == null) {
        error.errorCode = 3
        return -2
    }

    val record = data.students.indexOfFirst { it.idStudent == index }

    if (record == -1){
        error.errorCode = 4
        return record
    }

    return record
}

fun findInData(data: UniversatyData?, error: error, searсhList: List<Any?>): List<Student>{
    val result = mutableListOf<Student>()
    if (data == null) {
        error.errorCode = 3
        return result
    }

    for (student in data.students){
        if (student.idStudent == searсhList[0]){
            result.add(student)

        }
        if (student.nameStudent == searсhList[1]){ result.add(student)
        continue}
        if (student.secNameStudent == searсhList[2]){ result.add(student)
        continue}
        if (student.faculityStudent == searсhList[3]){ result.add(student)
        continue}
        val searchAchievements = searсhList[4] as? List<String> ?: emptyList()
        if (student.achievements.any { it.sport in searchAchievements }) {
            result.add(student)
            continue
        }
    }

    if (result.isEmpty()){
        error.errorCode = 6
        return result
    }

    return result
}

//fun changeRecord(index: Int, data: UniversatyData?, error: error): Boolean{
//    if (data == null) {
//        error.errorCode = 3
//        return false
//    }
//
//
//
//    return true
//}