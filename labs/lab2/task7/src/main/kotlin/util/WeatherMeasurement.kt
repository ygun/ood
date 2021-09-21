package util

import java.lang.IllegalArgumentException

data class WeatherMeasurement(
    var temperature: Double = 0.0,
    var humidity: Double = 0.0,
    var pressure: Double = 0.0,
    var windSpeed: Double = 0.0,
    var windDirection: Double = 0.0,
) : DataClass {

    override fun getFieldNames(): Set<String> {
        return setOf("temperature", "humidity", "pressure", "windSpeed", "windDirection")
    }

    override fun getFieldByName(name : String): Any {
        return when(name) {
            "temperature" -> temperature
            "humidity" -> humidity
            "pressure" -> pressure
            "windSpeed" -> windSpeed
            "windDirection" -> windDirection
            else -> throw IllegalArgumentException("Not correct name: $name")
        }
    }

    override fun updateFieldByName(name: String, newValue: Any) {
        when(name) {
            "temperature" -> temperature = newValue as Double
            "humidity" -> humidity = newValue as Double
            "pressure" -> pressure = newValue as Double
            "windSpeed" -> windSpeed = newValue as Double
            "windDirection" -> windDirection = newValue as Double
            else -> throw IllegalArgumentException("Not correct name: $name")
        }
    }
}