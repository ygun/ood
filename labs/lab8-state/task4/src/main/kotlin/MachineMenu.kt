import machine.MultiGumballMachine
import java.io.InputStream
import java.io.PrintStream
import java.util.*

class MachineMenu(
    private val machine: MultiGumballMachine,
    private val input: InputStream = System.`in`,
    private val output: PrintStream = System.out
) {
    companion object {
        const val END = "end"
        const val HELP = "help"
        const val INSERT_QUARTER = "insertQuarter"
        const val EJECT_QUARTER = "ejectQuarter"
        const val TURN_CRANK = "turnCrank"
        const val DISPENSE = "dispense"
        const val GET_COUNT_QUARTERS = "getCountQuarters"
        const val TO_STRING = "toString"
    }

    fun run() {
        output.println("For help type: help")

        val sc = Scanner(input)
        var userIn = ""
        while (userIn != END) {
            userIn = sc.nextLine()
            when (userIn) {
                END -> {
                    output.println("Bye")
                    break
                }
                HELP -> output.println(getHelp())
                INSERT_QUARTER -> machine.insertQuarter()
                EJECT_QUARTER -> machine.ejectQuarter()
                TURN_CRANK -> machine.turnCrank()
                DISPENSE -> machine.dispense()
                GET_COUNT_QUARTERS -> output.println("Machine contains: ${machine.getCountQuarters()} quarters")
                TO_STRING -> output.println(machine.toString())
            }
            output.println()
        }
    }

    private fun getHelp() = "Available commands: " +
            "\n\tinsertQuarter" +
            "\n\tejectQuarter" +
            "\n\tturnCrank" +
            "\n\tdispense" +
            "\n\taddQuarter" +
            "\n\tgetCountQuarters" +
            "\n\ttoString"
}