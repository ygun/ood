package resource

import java.io.File
import java.nio.file.Path

class FileRepository(pathToRepository: Path) : IResourceRepository {

    private val pathToFolder = pathToRepository.toUri().path + "/"

    init {
        val directory = File(pathToRepository.toUri())
        directory.mkdirs()
    }

    private val fileByName: MutableMap<String, File> = mutableMapOf()

    override fun add(file: File) {
        val copyFile = File(pathToFolder + file.name)
        file.copyTo(copyFile, true)
        fileByName.putIfAbsent(file.name, copyFile)
    }

    override fun delete(file: File) {
        file.delete()
    }

    override fun clear() {
        fileByName.forEach { (_, v) -> v.delete() }
        fileByName.clear()
    }
}