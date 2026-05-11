package workWithDATA
import workWithFile.*
import PrintConsole.error

//data class agrPock(
//    var midPock: MutableMap<String, MutableList<String>>,
//    var sumPock: MutableMap<String, MutableList<String>>,
//    var minPock: MutableMap<String, MutableList<String>>,
//    var maxPock: MutableMap<String, MutableList<String>>)

data class AgrPock(
    var midPock: MutableMap<String, Double>,
    var sumPock: MutableMap<String, Double>,
    var minPock: MutableMap<String, Double>,
    var maxPock: MutableMap<String, Double>
)

fun addRecord(data: UniversatyData?, error: error, index: Int): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }

    //if (index > 0 && findRecordByIndex(data, error, index) > 0) else return false


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

fun sortData(data: UniversatyData?, error: error, sortList: List<Boolean>): Boolean{
    if (data == null) {
        error.errorCode = 3
        return false
    }

    if (sortList[0]) data.students.sortBy { it.idStudent }
    if (sortList[1]) data.students.sortBy { it.nameStudent }
    if (sortList[2]) data.students.sortBy { it.secNameStudent }
    if (sortList[3]) data.students.sortBy { it.faculityStudent }
    if (sortList[4]) data.students.sortBy { it.achievements.size }

    return true
}

//fun agrData(data: UniversatyData?, error: error): agrPock?{
//    if (data == null) {
//        error.errorCode = 3
//        return null
//    }
//
//    val midPokaz = mutableMapOf<String, MutableList<String>>()
//    val sumPokaz = mutableMapOf<String, MutableList<String>>()
//    val minPokaz = mutableMapOf<String, MutableList<String>>()
//    val maxPokaz = mutableMapOf<String, MutableList<String>>()
//
//
//
//    return agrPock()
//}

fun parseResult(result: String): Pair<Double, String>? {

    val regex = Regex("""(\d+(\.\d+)?)\s*(\w+)""")

    val match = regex.find(result) ?: return null

    val value = match.groupValues[1].toDouble()
    val unit = match.groupValues[3]

    return Pair(value, unit)
}

fun agrData(data: UniversatyData?, error: error): AgrPock? {

    if (data == null) {
        error.errorCode = 3
        return null
    }

    val valuesByUnit = mutableMapOf<String, MutableList<Double>>()

    for (student in data.students) {
        for (ach in student.achievements) {

            val parsed = parseResult(ach.result)

            if (parsed != null) {

                val (value, unit) = parsed

                valuesByUnit
                    .getOrPut(unit) { mutableListOf() }
                    .add(value)
            }
        }
    }

    val midMap = mutableMapOf<String, Double>()
    val sumMap = mutableMapOf<String, Double>()
    val minMap = mutableMapOf<String, Double>()
    val maxMap = mutableMapOf<String, Double>()

    for ((unit, values) in valuesByUnit) {

        midMap[unit] = values.average()
        sumMap[unit] = values.sum()
        minMap[unit] = values.min()
        maxMap[unit] = values.max()
    }

    return AgrPock(
        midMap,
        sumMap,
        minMap,
        maxMap
    )
}