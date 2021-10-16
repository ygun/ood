package history

import command.ICommand
import document.IDocument

class CommandHistory : IHistory {

    companion object {
        const val MAX_HISTORY_SIZE = 10
    }

    private val commands: MutableList<ICommand> = mutableListOf()
    private var currIndex: Int = 0

    override fun addAndExecuteCommand(command: ICommand, document: IDocument) {
        if (!atTop() && commands.isNotEmpty()) {
            var i = commands.size - 1
            while (i >= currIndex) {
                commands[i].dispose()
                commands.removeAt(i)
                i--
            }
        }

        command.execute(document)

        commands.add(command)

        if (currIndex != MAX_HISTORY_SIZE) {
            currIndex++
        }

        if (commands.size > MAX_HISTORY_SIZE) {
            commands.removeAt(0)
        }
    }

    override fun undo(document: IDocument) {
        if (isEmpty()) {
            throw RuntimeException("Unable to execute undo, because the history is empty")
        }

        if (atBottom()) {
            throw RuntimeException("Unable to execute redo, because it's already at the bottom of the history")
        }

        commands[currIndex - 1].revert(document)
        currIndex--
    }

    override fun redo(document: IDocument) {
        if (isEmpty()) {
            throw RuntimeException("Unable to execute redo, because the history is empty")
        }

        if (atTop()) {
            throw RuntimeException("Unable to execute redo, because it's already at the top of the history")
        }

        commands[currIndex].execute(document)
        currIndex++
    }

    override fun isEmpty(): Boolean = commands.isEmpty()

    override fun atTop(): Boolean = currIndex == commands.size

    override fun atBottom(): Boolean = currIndex == 0
}