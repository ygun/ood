package task2

import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement

class Task2Test {
    @Test
    fun `Test display element with thy to delete himself in update `() {
        val weatherData = WeatherData()

        val displayElementTest1 = DisplayElementTest(weatherData)
        val displayElementTest2 = DisplayElementTest(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0))
        println()
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.0))
        println()
    }
}