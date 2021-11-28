package document

import document.element.image.IImage
import document.element.paragraph.IParagraph
import document.item.ConstDocumentItem
import document.item.DocumentItem
import java.nio.file.Path

interface IDocument {

    fun getTitle(): String
    fun setTitle(title: String)

    fun insertParagraph(text: String, position: Int? = null): IParagraph

    fun insertImage(path: Path, width: Int, height: Int, position: Int? = null): IImage

    fun getItemsCount(): Int

    fun getConstItem(index: Int): ConstDocumentItem
    fun getItem(index: Int): DocumentItem

    fun deleteItem(index: Int)

    fun canUndo(): Boolean
    fun undo()

    fun canRedo(): Boolean
    fun redo()

    fun save(path: Path)
}