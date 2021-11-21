package store

import command.ICommand
import util.CommandType
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class CommandStore : IStore<CommandType, ICommand> {

    private val commands: MutableMap<CommandType, KClass<out ICommand>> = TreeMap()

    override fun add(type: CommandType, instance: KClass<out ICommand>) {
        if (commands.containsKey(type)) {
            throw IllegalArgumentException("Command: $type, is already exist")
        }

        commands[type] = instance
    }

    override fun get(type: CommandType): KFunction<ICommand> {
        val command = commands[type] ?: throw IllegalArgumentException("Command: $type, doesn't exist")
        return command.constructors.first()
    }
}