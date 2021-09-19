package task2

import org.junit.jupiter.api.Test
import subject.WeatherData
import context.ContextBasic

class Task2Test {
    @Test
    fun `Test display element with thy to delete himself in update `() {
        val weatherData = WeatherData()

        val displayElementTest1 = DisplayElementTest(weatherData)
        val displayElementTest2 = DisplayElementTest(weatherData)

        weatherData.setContext(ContextBasic(80f, 65f, 30.4f))
        println()
        weatherData.setContext(ContextBasic(82f, 70f, 29.2f))
        println()
    }
}