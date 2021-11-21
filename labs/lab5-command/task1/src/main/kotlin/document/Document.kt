package document

import document.converter.HtmlConverter
import document.converter.IConverter
import document.element.image.IImage
import document.element.image.Image
import document.element.paragraph.IParagraph
import document.element.paragraph.Paragraph
import document.item.ConstDocumentItem
import document.item.DocumentItem
import history.CommandHistory
import java.io.File
import java.nio.file.Path
import java.util.*

class Document(
    private val history: CommandHistory = CommandHistory(),
    private var items: LinkedList<DocumentItem> = LinkedList(),
    private var title: String = "New Document",
    private val converter: IConverter = HtmlConverter()
) : IDocument {

    override fun getTitle(): String = title

    override fun setTitle(title: String) {
        this.title = title
    }

    override fun insertParagraph(text: String, position: Int?): IParagraph {
        val paragraph = Paragraph(text)
        val documentItem = DocumentItem(null, paragraph)

        insertItemToItems(position, documentItem)

        return paragraph
    }

    override fun insertImage(path: Path, width: Int, height: Int, position: Int?): IImage {
        val image = Image(path, width, height)
        val documentItem = DocumentItem(image, null)

        insertItemToItems(position, documentItem)

        return image
    }

    private fun insertItemToItems(position: Int?, documentItem: DocumentItem) {
        if (position != null) {
            ensureIndexValid(position)
            items.add(position, documentItem)
        } else {
            items.add(documentItem)
        }
    }

    override fun getItemsCount(): Int = items.size

    override fun getConstItem(index: Int): ConstDocumentItem {
        ensureIndexValid(index)
        val item = items[index]
        return ConstDocumentItem(item.image, item.paragraph)
    }

    override fun getItem(index: Int): DocumentItem {
        ensureIndexValid(index)
        return items[index]
    }

    override fun deleteItem(index: Int) {
        ensureIndexValid(index)
        items.removeAt(index)
    }

    private fun ensureIndexValid(index: Int) {
        if (index < 0 || index > items.size)
            throw IllegalArgumentException("Not correct index: $index")
    }

    override fun canUndo(): Boolean = !history.isEmpty() && !history.atBottom()

    override fun undo() = history.undo(this)

    override fun canRedo(): Boolean = !history.isEmpty() && !history.atTop()

    override fun redo() = history.redo(this)

    override fun save(path: Path) {
        val imagesPath = createFolder(path)
        copyImagesToDocumentFolder(imagesPath)

        val content = converter.convert(this)
        File(path.toUri()).printWriter().use { out -> out.println(content) }
    }

    private fun copyImagesToDocumentFolder(imagesPath: Path) {
        (0 until getItemsCount()).forEach {
            val item = getItem(it)
            if (item.image != null) {
                val image = File(item.image!!.getPath().toUri())
                val pathToCopyImage = Path.of(imagesPath.toString() + "/" + image.name)
                val copyFile = File(pathToCopyImage.toUri())
                image.copyTo(copyFile, true)
            }
        }
    }

    private fun createFolder(path: Path): Path {
        val parentPath = path.parent.toString()
        val imagesPath = Path.of("$parentPath/images")
        val imagesFolder = File(imagesPath.toUri())
        imagesFolder.mkdirs()
        return imagesPath
    }
}