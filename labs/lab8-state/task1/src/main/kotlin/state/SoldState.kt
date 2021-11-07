package state

import gumball.machine.IGumballMachine
import java.io.PrintStream

class SoldState(
    private val machine: IGumballMachine,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() = output.println("Please wait, we're already giving you a gumball")

    override fun ejectQuarter() = output.println("Sorry you already turned the crank")

    override fun turnCrank() = output.println("Turning twice doesn't get you another gumball")

    override fun dispense() {
        machine.releaseBall()
        if (machine.getBallCount() == 0) {
            output.println("Oops, out of gumballs")
            machine.setSoldOutState()
        } else {
            machine.setNoQuarterState()
        }
    }

    override fun toString() = "delivering a gumball"
}