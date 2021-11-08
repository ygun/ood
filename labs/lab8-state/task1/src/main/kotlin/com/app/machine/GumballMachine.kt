package com.app.machine

import java.io.PrintStream

class GumballMachine(
    private var count: Int = 0,
    output: PrintStream = System.out
) {

    private val machineImpl = GumballMachineImpl(count, output)

    fun insertQuarter() = machineImpl.insertQuarter()

    fun ejectQuarter() = machineImpl.ejectQuarter()

    fun turnCrank() = machineImpl.turnCrank()

    fun dispense() = machineImpl.dispense()

    fun fillMachine(ballsCount: Int) {
        count = ballsCount
        if (count > 0) {
            machineImpl.setNoQuarterState()
        }
    }

    override fun toString(): String {
        return "Mighty Gumball, Inc.\n" +
                "Inventory: $count gumballs\n" +
                "Machine is ${machineImpl.getState()}"
    }
}