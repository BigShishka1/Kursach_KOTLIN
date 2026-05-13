package PrintConsole
import workWithFile.UniversatyData
import workWithFile.Student
import workWithDATA.AgrPock

data class error (var errorCode: Int)

fun printMainMenu() { //Вывод всех функций
    println("""
+----------------------------------+
|            COMMANDS              |
+----------------------------------+
|                                  |
|  [1]  View commands              |
|  [2]  View DATA                  |
|  [3]  Load JSON                  |
|  [4]  Load CSV                   |
|  [5]  Add DATA                   |
|  [6]  Del DATA                   |
|  [7]  Export to JSON             |
|  [8]  Export to CSV              |
|  [9]  Change record              |
|  [10] Find record                |
|  [11] Sorted DATA                |
|  [12] Aggregate indicators       |
|                                  |
+----------------------------------+
|  Enter your choice (1-12):       |
+----------------------------------+
""")
}

fun wrongInput(){ //Некорректный ввод
    println("""
+-----------------------------------------+
|  !         INCORRECT INPUT            ! |
+-----------------------------------------+
""")
}

fun wrongNumb(){ //Неверная команда
    println("""
+-----------------------------------------+
|  !           WRONG NUMB              !  |
+-----------------------------------------+
""")
}

fun newRecordGuid(){ //Инструкция при добавлении записи
    println("""
+---------------------------------------------+
|  !            New record guid            !  |
+---------------------------------------------+
|                                             |
|      1) New ID                              |
|      2) New Name                            |
|      3) New second name                     |
|      4) New faculty                         |
|      5) New achievments (Through a space)   |
|      5.1) New result                        |
|      5.2) New date                          |
|      (reapet => achievments count)          |
|                                             |
+---------------------------------------------+
""")
}

fun chandeRecordGuid(){ //Инструкция при изменении записи
    println("""
+---------------------------------------------+
|  !         Change record guid            !  |
+---------------------------------------------+
|                                             |
|      1) ID record                           |
|      2) Create record again                 |
|                                             |
+---------------------------------------------+
""")
}

fun successNewDATA(){ //Новая запись добавлена
    println("""
+-----------------------------------------+
|  !           NEW DATA CREATED        !  |
+-----------------------------------------+
""")
}

fun emptyDATA(){ //Данные не сформированы
    println("""
+-----------------------------------------+
|  !           DATA IS EMPTY           !  |
+-----------------------------------------+
""")
}

fun formedDATA() { //Данные сформированы
    println("""
+-----------------------------------------+
|  !           FORMED DATA             !  |
+-----------------------------------------+
""")
}

fun dataDel() //Запись удалены
{
    println("""
+-----------------------------------------+
|  !           DATA DELETE             !  |
+-----------------------------------------+
""")
}

fun dataNotFound() { //Запись не найдена
    println("""
+-----------------------------------------+
|  !           DATA NOT FOUND          !  |
+-----------------------------------------+
""")
}

fun errorLoadData(){ //Ошибка загрузки данных из файла
    println("""
+-----------------------------------------+
|  !          ERROR LOAD DATA          !  |
+-----------------------------------------+
""")
}

fun dataSave(path: String){ //Данные экспортированы в файл
    println("""
+-----------------------------------------+
         DATA SAVE AS ${path}          
+-----------------------------------------+
""")
}

fun recordChange(){ //Запись изменена
    println("""
+-----------------------------------------+
|  !          Record changed           !  |
+-----------------------------------------+
""")
}

fun notFoundRecord(){ //Запись не найдена
    println("""
+-----------------------------------------+
|  !          Not found record         !  |
+-----------------------------------------+
""")
}

fun dataSorted(){ // Данные отсортированы
    println("""
+-----------------------------------------+
|  !          DATA sorted              !  |
+-----------------------------------------+
""")
}


fun printDATA(data: UniversatyData?, error: error): Boolean{ //Ввод всех записей
    if (data == null) {
        error.errorCode = 3
        return false
    }

    println("\n+----------------DATA---------------------+")
    for (student in data.students) printElementDATA(student)
    println("+-----------------------------------------+\n")

    return true
}

fun printElementDATA(student: Student){ // Вывод одной записи
    println("ID: ${student.idStudent}")
    println("   NAME: ${student.nameStudent}")
    println("   SECOND_NAME: ${student.secNameStudent}")
    println("   FACULTY: ${student.faculityStudent}")
    println("   Achievents:")
    for (achivment in student.achievements){
        println("       SPORT: ${achivment.sport}")
        println("           DATE: ${achivment.date}")
        println("           RESULT: ${achivment.result}")
    }
}

fun printError(error: error){ //Функция вывода ошибок
    val errorCode = error.errorCode
    when(errorCode){
        1 -> wrongInput()
        2 -> wrongNumb()
        3 -> emptyDATA()
        4 -> dataNotFound()
        5 -> errorLoadData()
        6 -> notFoundRecord()
    }
}

fun printAgr(pockz: AgrPock) { //Вывод агрегированных показателей

    println("""
+-----------------------------------------+
|         Aggregate indicators            |
+-----------------------------------------+
""".trimIndent())

    println("MID:")
    pockz.midPock.forEach {
        println("   ${it.key}: ${it.value}")
    }

    println("\nSUM:")
    pockz.sumPock.forEach {
        println("   ${it.key}: ${it.value}")
    }

    println("\nMIN:")
    pockz.minPock.forEach {
        println("   ${it.key}: ${it.value}")
    }

    println("\nMAX:")
    pockz.maxPock.forEach {
        println("   ${it.key}: ${it.value}")
    }

    println("+-----------------------------------------+")
}