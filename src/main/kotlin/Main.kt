import PrintConsole.* //Перенёс выоды в отдельную консоль packege
import workWithDATA.addRecord
import workWithDATA.delRecord
import workWithDATA.findInData
import workWithFile.*

val pathJSON = "inputJSON.json"
val pathCSV = "inputCSV.csv"
val pathSaveJSON = "outputJSON.json"
val pathSaveCSV = "outputCSV.csv"
var universityDATA: UniversatyData? = null


fun executionCommand(command: Int, data: UniversatyData?, error: error) {
    when (command) {
        1 -> printMainMenu()
        2 -> if (printDATA(data, error)) else printError(error)
        3 -> {universityDATA = loadDataFromJson(pathJSON, error)
            if (universityDATA == null) printError(error) else formedDATA() }
        4 -> {universityDATA = loadFromCSV(pathCSV, error)
            if (universityDATA == null) printError(error) else formedDATA()}
        5 -> { newRecordGuid()
            if (addRecord(data, error, -1)) successNewDATA() else printError(error)}
        6 -> {
            print("Deleted record index: ")
            if(delRecord(data,error)) dataDel() else printError(error)
        }
        7 -> if (saveToJson(pathSaveJSON, data, error)) dataSave(pathSaveJSON) else printError(error)
        8 -> if (saveToCSV(pathSaveCSV,data,error)) dataSave(pathSaveCSV) else printError(error)
        9 -> {
            chandeRecordGuid()
            val index = readln().toIntOrNull()
            if (index == null) {
                wrongInput()
                return
            }
            newRecordGuid()
            if (addRecord(data, error, index)) recordChange() else printError(error)
        }
        10 -> {
            println("Search record (intput: 123 / nothing )")
            print("ID student: ")
            var inputID: Int? = readln().toIntOrNull()
            print("Name student: ")
            var inputName = readln()
            print("Second name student: ")
            var inputSecName = readln()
            print("Faculity student: ")
            var inputFaculity = readln()
            print("Achievements student (separated by a space): ")
            var inputAchievements = readln().split(" ")
            val searchList = listOf(inputID, inputName, inputSecName, inputFaculity, inputAchievements)
            val studentList = findInData(data,error,searchList)
            if (studentList.isEmpty()) printError(error) else {
                println("+------Result search recorf---------+")
                studentList.forEach { printElementDATA(it) }
            }

        }
        else -> wrongNumb()
    }
}

fun main() {
    val error = error(0)
    printMainMenu()
    var command: Int?
    while (true) {
        command = readln().toIntOrNull()
        if (command == null) {
            wrongInput()
            continue
        } else executionCommand(command, universityDATA, error)
    }
}