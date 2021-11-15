package state

import machine.IGumballMachinePrivate
import machine.MAX_QUARTERS_COUNT
import java.io.PrintStream

class HasQuarterState(
    private val machine: IGumballMachinePrivate,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() {
        if (machine.getCountQuarters() >= MAX_QUARTERS_COUNT) {
            output.println("You can't insert quarters more than $MAX_QUARTERS_COUNT")
        } else {
            machine.addQuarter()
            output.println("You insert quarter, now machine has ${machine.getCountQuarters()} quarters")
        }
    }

    override fun ejectQuarter() {
        output.println("${machine.getCountQuarters()} quarters returned")
        machine.removeAllQuarters()
        machine.setNoQuarterState()
    }

    override fun turnCrank() {
        if (machine.getCountQuarters() <= 0) {
            output.println("Please insert a quarters")
            return
        }

        output.println("You turned...")
        machine.releaseBall()

        if (machine.getBallCount() == 0) {
            machine.setSoldState()
        } else if (machine.getCountQuarters() == 0) {
            machine.setNoQuarterState()
        }
    }

    override fun dispense() = output.println("No gumball dispensed")

    override fun fillMachine(ballsCount: Int) {
        machine.fillMachine(ballsCount)

        output.println("You've filled machine with: $ballsCount balls")
    }

    override fun toString() = "waiting for turn of crank"
}