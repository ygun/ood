package observer.statistic

import observer.DisplayElement
import observer.Observer
import context.Context
import context.ContextBasicFields

abstract class StatisticsDisplay(
    private val observableField: ContextBasicFields
) : Observer, DisplayElement {

    private var maxValue = Float.MIN_VALUE
    private var minValue = Float.MAX_VALUE
    private var valueSum = 0f
    private var numReadings = 0

    override fun update(context: Context) {
        val contextField = when (observableField) {
            ContextBasicFields.TEMPERATURE -> context.temperature
            ContextBasicFields.HUMIDITY -> context.humidity
            ContextBasicFields.PRESSURE -> context.pressure
        }
        numReadings++

        valueSum += contextField

        if (contextField > maxValue) {
            maxValue = contextField
        }
        if (contextField < minValue) {
            minValue = context.temperature
        }

        display()
    }

    override fun display() {
        println("Avg/Max/Min ${observableField}: ${valueSum / numReadings}/$maxValue/$minValue")
    }
}