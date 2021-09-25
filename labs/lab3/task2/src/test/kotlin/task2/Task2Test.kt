package task2

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size
import com.app.component.coffee.Cappuccino
import com.app.component.coffee.Espresso
import com.app.component.tea.Tea
import com.app.component.tea.TeaType
import com.app.decorator.Chocolate
import com.app.decorator.Cream
import com.app.decorator.Liquor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class Task2Test {

    @Test
    fun `Test Cream workflow`() {
        val blackTea: Beverage = Cream(Tea(TeaType.BLACK))
        println("$blackTea")
        assert(blackTea.description == "Standard Black Tea, Cream")
        assert(blackTea.cost() == 2.22)
        assert(blackTea.size == Size.MIDDLE)
        assert(blackTea.portion == Portion.STANDARD)

        val espresso: Beverage = Chocolate(Cream(Espresso()))
        println("$espresso")
        assert(espresso.description == "Espresso, Cream, Chocolate")
        assert(espresso.cost() == 2.53)
        assert(espresso.size == Size.MIDDLE)
        assert(espresso.portion == Portion.STANDARD)
    }

    @Test
    fun `Test Chocolate workflow`() {
        val espresso: Beverage = Chocolate(Espresso())
        println("$espresso")
        assert(espresso.description == "Espresso, Chocolate")
        assert(espresso.cost() == 2.13)
        assert(espresso.size == Size.MIDDLE)
        assert(espresso.portion == Portion.STANDARD)

        val espressoWithChocolate: Beverage = Chocolate(Espresso(), 4)
        println("$espressoWithChocolate")
        assert(espressoWithChocolate.description == "Espresso, 4 Chocolate Squares")
        assert(espressoWithChocolate.cost() == 2.55)
        assert(espressoWithChocolate.size == Size.MIDDLE)
        assert(espressoWithChocolate.portion == Portion.STANDARD)

        assertThrows<IllegalArgumentException> { Chocolate(Espresso(), -1) }
        assertThrows<IllegalArgumentException> { Chocolate(Espresso(), 0) }
        assertThrows<IllegalArgumentException> { Chocolate(Espresso(), 6) }
    }

    @Test
    fun `Test Liquor workflow`() {
        val cappuccinoWithLiquor: Beverage = Liquor(Cappuccino())
        println("$cappuccinoWithLiquor")
        assert(cappuccinoWithLiquor.description == "Standard Cappuccino, Nut Liquor")
        assert(cappuccinoWithLiquor.cost() == 1.79)
        assert(cappuccinoWithLiquor.size == Size.MIDDLE)
        assert(cappuccinoWithLiquor.portion == Portion.STANDARD)

        val cappuccinoWithChocolateLiquor: Beverage = Liquor(Cappuccino(), Liquor.LiquorType.CHOCOLATE)
        println("$cappuccinoWithChocolateLiquor")
        assert(cappuccinoWithChocolateLiquor.description == "Standard Cappuccino, Chocolate Liquor")
        assert(cappuccinoWithChocolateLiquor.cost() == 1.79)
        assert(cappuccinoWithChocolateLiquor.size == Size.MIDDLE)
        assert(cappuccinoWithChocolateLiquor.portion == Portion.STANDARD)
    }
}