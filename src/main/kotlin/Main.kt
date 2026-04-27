import PrintConsole.* //Перенёс выоды в отдельную консоль packege
import workWithDATA.addRecord
import workWithDATA.delRecord
import workWithFile.*

val pathJSON = "inputJSON.json"
val pathCSV = "inputCSV.csv"
var universityDATA: UniversatyData? = null

fun executionCommand(command: Int, data: UniversatyData?) {
    when (command) {
        1 -> printMainMenu()
        2 -> printDATA(data)
        3 -> universityDATA = loadDataFromJson(pathJSON)
        4 -> universityDATA = loadFromCSV(pathCSV)
        5 -> addRecord(data)
        6 -> delRecord(data)
        else -> wrongNumb()
    }
}

fun main() {
    printMainMenu()
    var command: Int?
    while (true) {
        command = readln().toIntOrNull()
        if (command == null) {
            wrongInput()
            continue
        } else executionCommand(command, universityDATA)
    }
}