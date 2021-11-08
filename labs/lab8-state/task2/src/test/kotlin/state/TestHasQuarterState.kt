package state

import machine.MultiGumballMachineImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestHasQuarterState {

    @Test
    fun `insertQuarter add quarter to Machine`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setHasQuarterState()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.insertQuarter()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(1, machineImpl.getCountQuarters())

        hasQuarterState.insertQuarter()
        hasQuarterState.insertQuarter()
        hasQuarterState.insertQuarter()
        hasQuarterState.insertQuarter()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(5, machineImpl.getCountQuarters())

        hasQuarterState.insertQuarter()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(5, machineImpl.getCountQuarters())
    }

    @Test
    fun `ejectQuarter change state to NoQuarter and return all quarters`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setHasQuarterState()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        assertEquals(3, machineImpl.getCountQuarters())

        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.ejectQuarter()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
        assertEquals(0, machineImpl.getCountQuarters())
    }

    @Test
    fun `turnCrank able to change state to SoldState`() {
        val machineImpl = MultiGumballMachineImpl(1)
        machineImpl.setHasQuarterState()
        machineImpl.addQuarter()
        val prevState = machineImpl.getState()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.turnCrank()
        assertTrue(machineImpl.getState() is SoldState)
        assertNotEquals(prevState, machineImpl.getState())
        assertEquals(0, machineImpl.getBallCount())
    }

    @Test
    fun `turnCrank able to change state to NoQuarter`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setHasQuarterState()
        machineImpl.addQuarter()
        val prevState = machineImpl.getState()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.turnCrank()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertNotEquals(prevState, machineImpl.getState())
        assertEquals(9, machineImpl.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setHasQuarterState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val hasQuarterState = HasQuarterState(machineImpl)

        hasQuarterState.dispense()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }
}