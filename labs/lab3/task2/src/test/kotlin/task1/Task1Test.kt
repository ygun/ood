package task1

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size
import com.app.component.*
import com.app.component.coffee.*
import com.app.component.tea.Tea
import com.app.component.tea.TeaType
import com.app.decorator.Milk
import com.app.decorator.Mocha
import com.app.decorator.Soy
import com.app.decorator.Whip
import org.junit.jupiter.api.Test

class Task1Test {

    @Test
    fun `Test workflow with default size`() {
        val beverageDecaf: Beverage = Decaf()
        println("$beverageDecaf")
        assert(beverageDecaf.description == "Decaf Coffee")
        assert(beverageDecaf.cost() == 1.05)
        assert(beverageDecaf.size == Size.MIDDLE)

        val beverageEspresso: Beverage = Espresso()
        println("$beverageEspresso")
        assert(beverageEspresso.description == "Espresso")
        assert(beverageEspresso.cost() == 1.99)
        assert(beverageEspresso.size == Size.MIDDLE)
    }

    @Test
    fun `Test workflow with decorator`() {
        val beverageDecaf: Beverage = Milk(Decaf())
        println("$beverageDecaf")
        assert(beverageDecaf.description == "Decaf Coffee, Milk")
        assert(beverageDecaf.cost() == 1.2)
        assert(beverageDecaf.size == Size.MIDDLE)

        val beverageEspresso: Beverage = Whip(Soy(Espresso()))
        assert(beverageEspresso.description == "Espresso, Soy, Whip")
        assert(beverageEspresso.cost() == 2.29)
        assert(beverageEspresso.size == Size.MIDDLE)
        println("$beverageEspresso")
    }

    @Test
    fun `Test nested workflow with size`() {
        val beverage: Beverage = Whip(Mocha(HouseBlend(Size.BIG)))
        println("$beverage")
        assert(beverage.description == "House Blend, Mocha, Whip")
        assert(beverage.cost() == 1.39)
        assert(beverage.size == Size.BIG)
    }

    @Test
    fun `Test Latte workflow`() {
        val latte: Beverage = Latte()
        println("$latte")
        assert(latte.description == "Standard Latte")
        assert(latte.cost() == 1.23)
        assert(latte.size == Size.MIDDLE)

        val nestedLatte: Beverage = Milk(Latte(Size.SMALL, Portion.DOUBLE))
        println("$nestedLatte")
        assert(nestedLatte.description == "Double Latte, Milk")
        assert(nestedLatte.cost() == 1.88)
        assert(nestedLatte.size == Size.SMALL)
        assert(nestedLatte.portion == Portion.DOUBLE)
    }

    @Test
    fun `Test Cappuccino workflow`() {
        val cappuccino: Beverage = Cappuccino()
        println("$cappuccino")
        assert(cappuccino.description == "Standard Cappuccino")
        assert(cappuccino.cost() == 1.10)
        assert(cappuccino.size == Size.MIDDLE)

        val nestedCappuccino: Beverage = Milk(Cappuccino(Size.SMALL, Portion.DOUBLE))
        println("$nestedCappuccino")
        assert(nestedCappuccino.description == "Double Cappuccino, Milk")
        assert(nestedCappuccino.cost() == 1.75)
        assert(nestedCappuccino.size == Size.SMALL)
        assert(nestedCappuccino.portion == Portion.DOUBLE)
    }

    @Test
    fun `Test Milkshake workflow`() {
        val milkshake: Beverage = Milkshake()
        println("$milkshake")
        assert(milkshake.description == "Standard Milkshake")
        assert(milkshake.cost() == 0.82)
        assert(milkshake.size == Size.MIDDLE)

        println()
        val nestedMilkshake: Beverage = Soy(Milkshake(Size.SMALL, Portion.DOUBLE))
        println("$nestedMilkshake")
        assert(nestedMilkshake.description == "Double Milkshake, Soy")
        assert(nestedMilkshake.cost() == 0.79)
        assert(nestedMilkshake.size == Size.SMALL)
        assert(nestedMilkshake.portion == Portion.DOUBLE)
    }

    @Test
    fun `Test Tea workflow`() {
        val greenTea = Tea(TeaType.GREEN)
        println("$greenTea")
        assert(greenTea.description == "Standard Green Tea")
        assert(greenTea.cost() == 1.92)
        assert(greenTea.size == Size.MIDDLE)
        assert(greenTea.portion == Portion.STANDARD)
        assert(greenTea.teaType == TeaType.GREEN)

        println()
        val blackTea = Milk(Tea(TeaType.BLACK, Size.SMALL, Portion.DOUBLE))
        println("$blackTea")
        assert(blackTea.description == "Double Black Tea, Milk")
        assert(blackTea.cost() == 1.79)
        assert(blackTea.size == Size.SMALL)
        assert(blackTea.portion == Portion.DOUBLE)
    }
}