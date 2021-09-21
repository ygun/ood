package display.statistic.wind

import display.statistic.StatisticsDisplay
import display.statistic.calculator.DegreeAverageCalculator
import subject.Subject
import util.WeatherMeasurement

class WindDirectionStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : StatisticsDisplay<WeatherMeasurement>(DegreeAverageCalculator()) {

    init {
        observableSubject.registerObserver(this, priority, WeatherMeasurement().getFieldNames())
    }

    override fun getFieldName() = "wind direction"
    override fun getFieldValue(context: WeatherMeasurement) = context.windDirection
}