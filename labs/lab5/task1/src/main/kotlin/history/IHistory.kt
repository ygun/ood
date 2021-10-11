package history

import command.ICommand
import document.IDocument

interface IHistory {
    fun addCommandAndExecute(command: ICommand, document: IDocument)

    fun undo(document: IDocument)
    fun redo(document: IDocument)

    fun isEmpty(): Boolean
    fun atTop(): Boolean
    fun atBottom(): Boolean
}