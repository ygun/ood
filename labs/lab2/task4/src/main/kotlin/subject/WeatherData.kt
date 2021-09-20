package subject

import util.WeatherMeasurement

class WeatherData : SubjectImpl<WeatherMeasurement>() {

    fun setContext(measurement: WeatherMeasurement) {
        super.contextChanged(measurement)
    }
}