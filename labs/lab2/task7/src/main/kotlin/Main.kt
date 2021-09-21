import display.CurrentConditionsDisplay
import display.ForecastDisplay
import display.HeatIndexDisplay
import display.statistic.HumidityStatisticsDisplay
import display.statistic.PressureStatisticsDisplay
import display.statistic.TemperatureStatisticsDisplay
import display.statistic.wind.WindDirectionStatisticsDisplay
import display.statistic.wind.WindSpeedStatisticsDisplay
import subject.WeatherData
import util.WeatherMeasurement

fun main() {
    val weatherData = WeatherData()

    val currentConditionsDisplay = CurrentConditionsDisplay(weatherData, 5)
    val temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherData, 1)
    val humidityStatisticsDisplay = HumidityStatisticsDisplay(weatherData, 100)
    val pressureStatisticsDisplay = PressureStatisticsDisplay(weatherData, 12)
    val forecastDisplay = ForecastDisplay(weatherData)
    val heatIndexDisplay = HeatIndexDisplay(weatherData)

    var speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherData)
    var directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherData)

    weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 5.0, 10.0))
    println()
    weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.2, 3.0, 15.0))
    println()
    weatherData.setContext(WeatherMeasurement(78.0, 90.0, 29.2, 1.0, 16.0))
}