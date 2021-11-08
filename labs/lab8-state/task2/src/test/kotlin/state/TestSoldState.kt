package state

import machine.MultiGumballMachineImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestSoldState {
    @Test
    fun `insertQuarter doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.insertQuarter()
        assertTrue(machineImpl.getState() is SoldState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state and return all quarters`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldState()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        assertEquals(3, machineImpl.getCountQuarters())

        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.ejectQuarter()
        assertTrue(machineImpl.getState() is SoldState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
        assertEquals(0, machineImpl.getCountQuarters())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.turnCrank()
        assertTrue(machineImpl.getState() is SoldState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `dispense able to change state to SoldOutState`() {
        val machineImpl = MultiGumballMachineImpl(1)
        machineImpl.setSoldState()
        machineImpl.addQuarter()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.dispense()
        assertTrue(machineImpl.getState() is SoldOutState)
        assertNotEquals(prevState, machineImpl.getState())
        assertNotEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `dispense able to change state to NoQuarter`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldState()
        machineImpl.addQuarter()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.dispense()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertNotEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `dispense able to change state to HasQuarter`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldState()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.dispense()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertNotEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `dispense able to change state to NoQuarterState`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldState()
        machineImpl.addQuarter()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldState = SoldState(machineImpl)

        soldState.dispense()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertNotEquals(prevBallsCount, machineImpl.getBallCount())
    }
}