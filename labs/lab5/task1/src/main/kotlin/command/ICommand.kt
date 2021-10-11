package command

import document.IDocument

interface ICommand {
    fun execute(document: IDocument)
    fun revert(document: IDocument)
    fun dispose()
}