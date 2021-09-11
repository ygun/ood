import com.app.behavior.dance.DanceWaltz
import com.app.behavior.fly.FlyRockerPower
import com.app.behavior.quack.Squeak
import com.app.duck.DecoyDuck
import com.app.duck.MallardDuck

fun main(args: Array<String>) {
    println("< Mallard Duck >")
    val mallard = MallardDuck()
    mallard.performFly()
    mallard.performQuack()
    mallard.quackBehavior = Squeak()
    println("< change quack behavior >")
    mallard.performQuack()
    mallard.performDance()

    println()

    println("< Decoy Duck >")
    val decoy = DecoyDuck()
    decoy.performFly()
    decoy.flyBehavior = FlyRockerPower()
    println("< change fly behavior >")
    decoy.performFly()
    decoy.performDance()
    println("< change dance behavior >")
    decoy.danceBehavior = DanceWaltz()
    decoy.performDance()
}