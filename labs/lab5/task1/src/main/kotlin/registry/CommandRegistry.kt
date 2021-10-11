package registry

import util.CommandType
import command.ICommand
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class CommandRegistry {

    private val commands: MutableMap<CommandType, KClass<out ICommand>> = TreeMap()

    fun register(type: CommandType, command: KClass<out ICommand>) {
        if (commands.containsKey(type)) {
            throw IllegalArgumentException("Command: $type, is already exist")
        }

        commands[type] = command
    }

    fun get(type: CommandType): KFunction<ICommand> {
        val command = commands[type] ?: throw IllegalArgumentException("Command: $type, doesn't exist")
        return command.constructors.first()
    }
}