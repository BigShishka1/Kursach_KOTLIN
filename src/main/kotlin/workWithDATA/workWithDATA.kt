package workWithDATA
import workWithFile.*
import PrintConsole.*

fun addRecord(data: UniversatyData?, error: error): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }
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
    data.students.add(newStudent)
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

private fun findRecordByIndex(data: UniversatyData?, error: error): Int{
    if (data == null) {
        error.errorCode = 3
        return -1
    }



}

fun changeRecord(index: Int, data: UniversatyData?, error: error): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }



    return true
}