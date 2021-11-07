package state

import gumball.machine.GumballMachine
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestSoldOutState {
    @Test
    fun `insertQuarter doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)
        println(machine.toString())

        soldOutState.insertQuarter()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)
        println(machine.toString())

        soldOutState.ejectQuarter()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)
        println(machine.toString())

        soldOutState.turnCrank()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldOutState = SoldOutState(machine)
        println(machine.toString())

        soldOutState.dispense()
        assertTrue(machine.getState() is SoldOutState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }
}