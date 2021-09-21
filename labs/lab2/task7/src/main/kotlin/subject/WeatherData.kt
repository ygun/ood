package subject

import util.WeatherMeasurement

class WeatherData : SubjectImpl<WeatherMeasurement>(
    WeatherMeasurement().getFieldNames()
) {

    fun setContext(measurement: WeatherMeasurement) {
        super.contextChanged(measurement)
    }
}