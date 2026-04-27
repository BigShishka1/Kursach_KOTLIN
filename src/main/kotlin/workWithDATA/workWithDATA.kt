package workWithDATA
import workWithFile.*
import PrintConsole.*

fun addRecord(data: UniversatyData?){
    if (data == null) {
        emptyDATA()
        return
    }
    newRecordGuid()
    val newID = readln().toIntOrNull() ?: -1
    if (newID == -1) {
        wrongInput()
        return
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
    successNewDATA()
}

fun delRecord(data: UniversatyData?){
    if (data == null) {
        emptyDATA()
        return
    }

    val id = readln().toIntOrNull()

    if (id == null)
    {
        wrongInput()
        return
    }

    val removed = data.students.removeIf { it.idStudent == id }

    if (removed) dataDel()
    else dataNotFound()
}