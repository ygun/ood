package task2

import subject.WeatherData
import util.Context
import java.util.Comparator

fun main() {
    val weatherData = WeatherData()

    val displayElementTest1 = DisplayElementTest(weatherData)
    val displayElementTest2 = DisplayElementTest(weatherData)

    weatherData.setContext(Context(80f, 65f, 30.4f))
    println()
    weatherData.setContext(Context(82f, 70f, 29.2f))
    println()


    var capitals = hashMapOf<String, Int?>()
    capitals.put("Nepal", 4)
    capitals.put("India", 8)
    capitals.put("United States", 1)
    capitals.put("England", 4)
    capitals.put("Australia", 2)
    capitals.put("Russia", null)
    val result = capitals.toList().sortedBy { (_, value) -> value }.toMap()
    val result2 = capitals.toList().sortedWith(compareBy(nullsLast()) { it.second }).toMap()

    for (entry in result2) {
        print("Key: " + entry.key)
        println(" Value: " + entry.value)
    }
}