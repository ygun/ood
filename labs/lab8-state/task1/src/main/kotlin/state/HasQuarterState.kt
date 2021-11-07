package state

import gumball.machine.IGumballMachine
import java.io.PrintStream

class HasQuarterState(
    private val machine: IGumballMachine,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() = output.println("You can't insert another quarter")

    override fun ejectQuarter() {
        output.println("Quarter returned")
        machine.setNoQuarterState()
    }

    override fun turnCrank() {
        output.println("You turned...")
        machine.releaseBall()
        if (machine.getBallCount() == 0) {
            machine.setSoldState()
        } else {
            machine.setNoQuarterState()
        }
    }

    override fun dispense() = output.println("No gumball dispensed")

    override fun toString() = "waiting for turn of crank"
}