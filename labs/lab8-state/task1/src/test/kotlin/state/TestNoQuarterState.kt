package state

import gumball.machine.GumballMachine
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestNoQuarterState {
    @Test
    fun `insertQuarter change state to HasQuarter`() {
        val machine = GumballMachine(10)
        val prevBallsCount = machine.getBallCount()

        val noQuarterState = NoQuarterState(machine)

        noQuarterState.insertQuarter()
        assertTrue(machine.getState() is HasQuarterState)
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state`() {
        val machine = GumballMachine(10)
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val noQuarterState = NoQuarterState(machine)
        println(machine.toString())

        noQuarterState.ejectQuarter()
        assertTrue(machine.getState() is NoQuarterState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machine = GumballMachine(10)
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val noQuarterState = NoQuarterState(machine)
        println(machine.toString())

        noQuarterState.turnCrank()
        assertTrue(machine.getState() is NoQuarterState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machine = GumballMachine(10)
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val noQuarterState = NoQuarterState(machine)
        println(machine.toString())

        noQuarterState.dispense()
        assertTrue(machine.getState() is NoQuarterState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }
}