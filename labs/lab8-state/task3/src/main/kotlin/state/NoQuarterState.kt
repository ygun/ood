package state

import machine.IGumballMachinePrivate
import java.io.PrintStream

class NoQuarterState(
    private val machine: IGumballMachinePrivate,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() {
        output.println("You inserted a quarter")
        machine.setHasQuarterState()
        machine.addQuarter()
    }

    override fun ejectQuarter() = output.println("You haven't inserted a quarter")

    override fun turnCrank() = output.println("You turned but there's no quarter")

    override fun dispense() = output.println("You need to pay first")

    override fun fillMachine(ballsCount: Int) {
        machine.fillMachine(ballsCount)

        output.println("You've filled machine with: $ballsCount balls")
    }


    override fun toString() = "waiting for quarter"
}