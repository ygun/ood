package repository

import org.junit.jupiter.api.Test
import resource.FileRepository
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestFileRepository {
    @Test
    fun `add copy file and create folder`() {
        val fileRepository = FileRepository(Path.of("src/test/kotlin/repository/copy_to"))

        fileRepository.add(File("src/test/kotlin/repository/copy_from/img.jpg"))
        val copiedFile = File("src/test/kotlin/repository/copy_to/img.jpg")
        assertTrue(copiedFile.isFile)
        assertFolderContainsAmountOfFiles(1, "src/test/kotlin/repository/copy_to")
    }

    @Test
    fun `delete remove file from folder`() {
        val fileRepository = FileRepository(Path.of("src/test/kotlin/repository/copy_to"))

        fileRepository.add(File("src/test/kotlin/repository/copy_from/img.jpg"))
        val copiedFile = File("src/test/kotlin/repository/copy_to/img.jpg")
        assertTrue(copiedFile.isFile)
        assertFolderContainsAmountOfFiles(1, "src/test/kotlin/repository/copy_to")

        fileRepository.delete(copiedFile)
        assertFolderContainsAmountOfFiles(0, "src/test/kotlin/repository/copy_to")
    }

    @Test
    fun `clear remove all files from folder`() {
        val fileRepository = FileRepository(Path.of("src/test/kotlin/repository/copy_to"))

        fileRepository.add(File("src/test/kotlin/repository/copy_from/img.jpg"))
        assertFolderContainsAmountOfFiles(1, "src/test/kotlin/repository/copy_to")

        fileRepository.add(File("src/test/kotlin/repository/copy_from/img2.jpg"))
        assertFolderContainsAmountOfFiles(2, "src/test/kotlin/repository/copy_to")

        fileRepository.clear()
        assertFolderContainsAmountOfFiles(0, "src/test/kotlin/repository/copy_to")
    }

    private fun assertFolderContainsAmountOfFiles(count: Int, path: String) {
        assertEquals(count, Path.of(path).toFile().listFiles().size)
    }
}