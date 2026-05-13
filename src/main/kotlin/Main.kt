import PrintConsole.* //Перенёс выоды в отдельную консоль packege
import workWithDATA.addRecord
import workWithDATA.delRecord
import workWithDATA.findInData
import workWithDATA.sortData
import workWithDATA.AgrPock
import workWithDATA.agrData


import workWithFile.*


//Пути к файлам + инициализираваная поле с данными
val pathJSON = "inputJSON.json"
val pathCSV = "inputCSV.csv"
val pathSaveJSON = "outputJSON.json"
val pathSaveCSV = "outputCSV.csv"
var universityDATA: UniversatyData? = null


// Функция выполняющая команды пользователя (Команда, данные, объекты ошибки)
fun executionCommand(command: Int, data: UniversatyData?, error: error) {
    when (command) {
        1 -> printMainMenu() //Вывод всех команды на экран
        2 -> if (printDATA(data, error)) else printError(error) //Вывод всех записей
        3 -> {universityDATA = loadDataFromJson(pathJSON, error) //Загрузка из JSON файла
            if (universityDATA == null) printError(error) else formedDATA() }
        4 -> {universityDATA = loadFromCSV(pathCSV, error) //Загрузка из CSV файла
            if (universityDATA == null) printError(error) else formedDATA()}
        5 -> { newRecordGuid() //Создание новой записи
            if (addRecord(data, error, -1)) successNewDATA() else printError(error)}
        6 -> { //Удаление записи
            print("Deleted record index: ")
            if(delRecord(data,error)) dataDel() else printError(error)
        }
        7 -> if (saveToJson(pathSaveJSON, data, error)) dataSave(pathSaveJSON) else printError(error) //Сохранение в JSON
        8 -> if (saveToCSV(pathSaveCSV,data,error)) dataSave(pathSaveCSV) else printError(error) //Сохранение в CSV
        9 -> { //Изменение записи
            chandeRecordGuid()
            val index = readln().toIntOrNull()
            if (index == null) {
                wrongInput()
                return
            }
            newRecordGuid()
            if (addRecord(data, error, index)) recordChange() else printError(error)
        }
        10 -> { //Поиск записей
            println("Search records (intput: 123 / nothing )")
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
        11 -> { //Сортировка записей
            println("Sorted records ( y / n )")
            print("ID student: ")
            val inputID = when (readln()) {
                "y" -> true
                "n" -> false
                else -> false
            }
            print("Name student: ")
            val inputName = when (readln()) {
                "y" -> true
                "n" -> false
                else -> false
            }
            print("Second name student: ")
            val inputSecName = when (readln()) {
                "y" -> true
                "n" -> false
                else -> false
            }
            print("Faculity student: ")
            val inputFaculity = when (readln()) {
                "y" -> true
                "n" -> false
                else -> false
            }
            print("Achievements student: ")
            val inputAchievements = when (readln()) {
                "y" -> true
                "n" -> false
                else -> false
            }
            val sortList: List<Boolean> = listOf<Boolean>(inputID, inputName, inputSecName,inputFaculity, inputAchievements)
            if (sortData(data,error,sortList)) dataSorted() else printError(error)
        }
        12 -> { //Вывод агригированых показателей
            val agrPock: AgrPock? = agrData(data,error)
            if (agrPock == null) printError(error) else printAgr(agrPock)
        }
        else -> wrongNumb()
    }
}

fun main() {
    val error = error(0) //Экземпляр класса error. Посылается во все функции, где сохраняет в себе код ошибки для последующей обработки
    printMainMenu()
    var command: Int?
    while (true) {
        command = readln().toIntOrNull()
        if (command == null) {
            wrongInput()
            continue
        } else executionCommand(command, universityDATA, error) //Выполнение команды (Сама команда, данные, объект ошибки)
    }
}