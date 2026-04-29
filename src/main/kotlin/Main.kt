import PrintConsole.* //Перенёс выоды в отдельную консоль packege
import workWithDATA.addRecord
import workWithDATA.delRecord
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
            if (universityDATA == null) printError(error) }
        4 -> {universityDATA = loadFromCSV(pathCSV, error)
            if (universityDATA == null) printError(error)}
        5 -> { newRecordGuid()
            if (addRecord(data, error)) successNewDATA() else printError(error)}
        6 -> if(delRecord(data,error)) dataDel() else printError(error)
        7 -> if (saveToJson(pathSaveJSON, universityDATA, error)) dataSave(pathSaveJSON) else printError(error)
        8 -> if (saveToCSV(pathSaveCSV,universityDATA,error)) dataSave(pathSaveCSV) else printError(error)
        //9 ->
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