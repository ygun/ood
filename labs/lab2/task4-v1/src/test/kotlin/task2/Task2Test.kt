package task2

import org.junit.jupiter.api.Test
import subject.SubjectType
import subject.WeatherData
import util.Context

class Task2Test {
    @Test
    fun `Test display element with thy to delete himself in update `() {
        val weatherDataIn = WeatherData(SubjectType.INPUT)
        val weatherDataOut = WeatherData(SubjectType.OUTPUT)

        val displayElementTest1 = DisplayElementTest(weatherDataIn, weatherDataOut)
        val displayElementTest2 = DisplayElementTest(weatherDataIn, weatherDataOut)

        weatherDataIn.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherDataIn.setContext(Context(82f, 70f, 29.2f))
        println()
    }
}