package command

import document.IDocument

class Macros(
    private val name: String,
    private val document: IDocument
) {
    private val commands = mutableListOf<ICommand>()

    fun addCommand(command: ICommand) {
        commands.add(command)
    }

    fun execute() = commands.toList().forEach { it.execute(document) }

    override fun toString(): String = "Macros : { \n" +
            "\tname : $name,\n " +
            "\tcommands = $commands)"
}