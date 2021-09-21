package display.statistic

import display.DisplayElement
import display.statistic.calculator.AverageCalculator
import display.statistic.calculator.DefaultAverageCalculator
import observer.Observer
import subject.Subject
import util.WeatherMeasurement

abstract class StatisticsDisplay<ObservableField>(
    private var avgStatisticCalculator: AverageCalculator = DefaultAverageCalculator()
) : Observer<ObservableField>, DisplayElement {

    private var maxValue = 0.0
    private var minValue = Double.MAX_VALUE
    private var valueSum = 0.0
    private var numReadings = 0

    abstract fun getFieldName(): String
    abstract fun getFieldValue(context: ObservableField): Double

    override fun update(context: ObservableField) {
        val newValue = getFieldValue(context)

        valueSum += newValue
        numReadings++

        if (newValue > maxValue) {
            maxValue = newValue
        }
        if (newValue < minValue) {
            minValue = newValue
        }

        display()
    }

    override fun display() {
        println("Avg/Max/Min ${getFieldName()}: ${valueSum / numReadings}/$maxValue/$minValue")
    }
}