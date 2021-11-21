package resource

import java.io.File

interface IResourceRepository {
    fun add(file: File)
    fun delete(file: File)
    fun clear()
}