package PrintConsole
import workWithFile.UniversatyData
import workWithFile.Student

data class error (var errorCode: Int)

fun printMainMenu() {
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
|                                  |
+----------------------------------+
|  Enter your choice (1-5):        |
+----------------------------------+
""")
}

fun wrongInput(){
    println("""
+-----------------------------------------+
|  !         INCORRECT INPUT            ! |
+-----------------------------------------+
""")
}

fun wrongNumb(){
    println("""
+-----------------------------------------+
|  !           WRONG NUMB              !  |
+-----------------------------------------+
""")
}

fun newRecordGuid(){
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

fun chandeRecordGuid(){
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

fun successNewDATA(){
    println("""
+-----------------------------------------+
|  !           NEW DATA CREATED        !  |
+-----------------------------------------+
""")
}

fun emptyDATA(){
    println("""
+-----------------------------------------+
|  !           DATA IS EMPTY           !  |
+-----------------------------------------+
""")
}

fun formedDATA()
{
    println("""
+-----------------------------------------+
|  !           FORMED DATA             !  |
+-----------------------------------------+
""")
}

fun dataDel()
{
    println("""
+-----------------------------------------+
|  !           DATA DELETE             !  |
+-----------------------------------------+
""")
}

fun dataNotFound()
{
    println("""
+-----------------------------------------+
|  !           DATA NOT FOUND          !  |
+-----------------------------------------+
""")
}

fun errorLoadData(){
    println("""
+-----------------------------------------+
|  !          ERROR LOAD DATA          !  |
+-----------------------------------------+
""")
}

fun dataSave(path: String){
    println("""
+-----------------------------------------+
         DATA SAVE AS ${path}          
+-----------------------------------------+
""")
}

fun recordChange(){
    println("""
+-----------------------------------------+
|  !          Record changed           !  |
+-----------------------------------------+
""")
}

fun notFoundRecord(){
    println("""
+-----------------------------------------+
|  !          Not found record         !  |
+-----------------------------------------+
""")
}


fun printDATA(data: UniversatyData?, error: error): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }

    println("\n+----------------DATA---------------------+")
    for (student in data.students) printElementDATA(student)
    println("+-----------------------------------------+\n")

    return true
}

fun printElementDATA(student: Student){
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

fun printError(error: error){
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