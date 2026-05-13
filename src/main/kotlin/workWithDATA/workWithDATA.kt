package workWithDATA
import workWithFile.*
import PrintConsole.error

data class AgrPock( //Класс, который будет хранить агрегированные показатели
    var midPock: MutableMap<String, Double>,
    var sumPock: MutableMap<String, Double>,
    var minPock: MutableMap<String, Double>,
    var maxPock: MutableMap<String, Double>
)

//Функция добавления записей (Данные, объект ошибки, флаг(функция меняет поведения в зависимости от значения))
fun addRecord(data: UniversatyData?, error: error, index: Int): Boolean{
    if (data == null) { //Проверка на пустоту данных
        error.errorCode = 3
        return false
    }

    val newID = readln().toIntOrNull() ?: -1
    if (newID == -1) {
        error.errorCode = 1
        return false
    }

    //Ввод полей для записи
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
    //Формирование новой записи
    var newStudent: Student = Student(newID, newName, newSecName, newFaculty, listAchievment)

    //Запись добавляется либо изменяется в зависимости от флага
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

// Удаление записи (Данные, объект ошибки)
fun delRecord(data: UniversatyData?, error: error): Boolean{
    if (data == null) { //Проверка на пустоту данных
        error.errorCode = 3
        return false
    }

    //Индекс удаления записи
    val id = readln().toIntOrNull()

    if (id == null) //Проверка правильности индекса
    {
        error.errorCode = 1
        return false
    }

    val removed = data.students.removeIf { it.idStudent == id } //Удаление записи

    if (removed) return true //Если запись удалена возвращает true
    else {
        error.errorCode = 4
        return false
    }
}

//Поиск записей (Данные, объект ошибки, список параметров для поиска)
fun findInData(data: UniversatyData?, error: error, searсhList: List<Any?>): List<Student>{
    val result = mutableListOf<Student>() //Список содержащий найденные записи
    if (data == null) { //Проверка на пустоту данных
        error.errorCode = 3
        return result
    }

    //Если значения поля подходит под условие студент заносится в список result
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

    if (result.isEmpty()){ //Если записи не нашлись функция завершается с ошибкой
        error.errorCode = 6
        return result
    }

    return result
}

//Сортировка данных (Данные, объект ошибки, список параметров для сортировки)
fun sortData(data: UniversatyData?, error: error, sortList: List<Boolean>): Boolean{
    if (data == null) { //Проверка на пустоты данных
        error.errorCode = 3
        return false
    }

    //Данные сортируется по параметрам
    if (sortList[0]) data.students.sortBy { it.idStudent }
    if (sortList[1]) data.students.sortBy { it.nameStudent }
    if (sortList[2]) data.students.sortBy { it.secNameStudent }
    if (sortList[3]) data.students.sortBy { it.faculityStudent }
    if (sortList[4]) data.students.sortBy { it.achievements.size }

    return true
}

//Функция, которая разбивает спортивное достижение на значение + вид, использует регулярные выражения
fun parseResult(result: String): Pair<Double, String>? {

    val regex = Regex("""(\d+(\.\d+)?)\s*(\w+)""")

    val match = regex.find(result) ?: return null

    val value = match.groupValues[1].toDouble()
    val unit = match.groupValues[3]

    return Pair(value, unit)
}

// Вычисление агрегированных показателей (Данные, объект ошибки)
fun agrData(data: UniversatyData?, error: error): AgrPock? {

    if (data == null) { //Проверка на пустоту данных
        error.errorCode = 3
        return null
    }
    //Коллекция для хранения полученных после парса результатов
    val valuesByUnit = mutableMapOf<String, MutableList<Double>>()

    //Перебор студентов
    for (student in data.students) {
        for (ach in student.achievements) {
            //Парс
            val parsed = parseResult(ach.result)

            if (parsed != null) {

                val (value, unit) = parsed

                //Если ключа ещё не было он создаётся + добавляется значения
                valuesByUnit
                    .getOrPut(unit) { mutableListOf() }
                    .add(value)
            }
        }
    }

    //Списки для значений полей, позже они используются для подсчёта
    val midMap = mutableMapOf<String, Double>()
    val sumMap = mutableMapOf<String, Double>()
    val minMap = mutableMapOf<String, Double>()
    val maxMap = mutableMapOf<String, Double>()

    //Пересчёт агрегированных состояний
    for ((unit, values) in valuesByUnit) {
        midMap[unit] = values.average()
        sumMap[unit] = values.sum()
        minMap[unit] = values.min()
        maxMap[unit] = values.max()
    }

    //Возврат итогового объект содержащего эти параметры
    return AgrPock(
        midMap,
        sumMap,
        minMap,
        maxMap
    )
}