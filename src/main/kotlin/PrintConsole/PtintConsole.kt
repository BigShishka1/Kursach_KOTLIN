package PrintConsole
import workWithFile.UniversatyData

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

fun printDATA(data: UniversatyData?){
    if (data == null) {
        emptyDATA()
        return
    }
    println("\n+----------------DATA---------------------+")
    for (student in data.students){
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
    println("+-----------------------------------------+\n")
}