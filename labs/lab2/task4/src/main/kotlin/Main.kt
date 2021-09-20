import display.CurrentConditionsDisplay
import display.ForecastDisplay
import display.HeatIndexDisplay
import display.statistic.HumidityStaticsDisplay
import display.statistic.PressureStatisticsDisplay
import display.statistic.TemperatureStatisticsDisplay
import subject.WeatherData
import util.WeatherMeasurement

fun main() {
    val weatherDataIn = WeatherData()
    val weatherDataOut = WeatherData()

    val currentConditionsDisplay = CurrentConditionsDisplay(weatherDataIn, 5, weatherDataOut)
    val temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, 1, weatherDataOut, 2)
    val humidityStaticsDisplay = HumidityStaticsDisplay(weatherDataIn, 100, weatherDataOut, 100)
    val pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, 12, weatherDataOut, 12)
    val forecastDisplay = ForecastDisplay(weatherDataIn, outSubject = weatherDataOut)
    val heatIndexDisplay = HeatIndexDisplay(weatherDataIn, outSubject = weatherDataOut)

    weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0))
    println()
    weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
    println()
    weatherDataOut.setContext(WeatherMeasurement(78.0, 90.0, 29.2))
    println()
}