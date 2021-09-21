package util

interface DataClass {
    fun getFieldNames(): Set<String>
    fun getFieldByName(name : String): Any
    fun updateFieldByName(name: String, newValue: Any)
}