package machine

import java.io.PrintStream

class MultiGumballMachine(
    countBalls: Int = 0,
    output: PrintStream = System.out
) {

    private val machineImpl = MultiGumballMachineImpl(countBalls, output)

    fun insertQuarter() = machineImpl.insertQuarter()

    fun ejectQuarter() = machineImpl.ejectQuarter()

    fun turnCrank() = machineImpl.turnCrank()

    fun dispense() = machineImpl.dispense()

    fun getCountQuarters(): Int = machineImpl.getCountQuarters()

    fun addQuarter() = machineImpl.addQuarter()

    override fun toString(): String {
        return "Mighty Gumball, Inc.\n" +
                "Inventory: ${machineImpl.getBallCount()} gumballs\n" +
                "Count quarters: ${machineImpl.getCountQuarters()}\n" +
                "Machine is ${machineImpl.getState()}"
    }
}