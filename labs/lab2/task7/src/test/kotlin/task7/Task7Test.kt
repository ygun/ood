package task7

import display.FishingDisplay
import display.ForecastDisplay
import display.statistic.wind.WindDirectionStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement

class Task7Test {
    @Test
    fun `Test picked fields does not changed`() {
        val weatherData = WeatherData()

        val forecastDisplay = ForecastDisplay(weatherData)
        val fishingDisplay = FishingDisplay(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherData.setContext(WeatherMeasurement(80.0, 70.0, 30.0, 4.0, 50.0))
        println()
        weatherData.setContext(WeatherMeasurement(80.0, 70.0, 30.0, 4.0, 50.0))
        println()
    }

    @Test
    fun `Test picked fields was changed`() {
        val weatherData = WeatherData()

        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherData)
        val fishingDisplay = FishingDisplay(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherData.setContext(WeatherMeasurement(81.0, 70.0, 29.0, 4.0, 50.0))
        println()
        weatherData.setContext(WeatherMeasurement(79.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }
}