package state

import gumball.machine.GumballMachine
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestSoldState {
    @Test
    fun `insertQuarter doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldState = SoldState(machine)

        soldState.insertQuarter()
        assertTrue(machine.getState() is SoldState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldState = SoldState(machine)

        soldState.ejectQuarter()
        assertTrue(machine.getState() is SoldState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machine = GumballMachine(10)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldState = SoldState(machine)

        soldState.turnCrank()
        assertTrue(machine.getState() is SoldState)
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense able to change state to SoldOutState`() {
        val machine = GumballMachine(1)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldState = SoldState(machine)

        soldState.dispense()
        assertTrue(machine.getState() is SoldOutState)
        assertNotEquals(prevState, machine.getState())
        assertNotEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense able to change state to NoQuarterState`() {
        val machine = GumballMachine(10)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        val soldState = SoldState(machine)

        soldState.dispense()
        assertTrue(machine.getState() is NoQuarterState)
        assertNotEquals(prevState, machine.getState())
        assertNotEquals(prevBallsCount, machine.getBallCount())
    }
}