package util

enum class CommandType {

    INSERT_PARAGRAPH {
        override fun toString(): String = "InsertParagraph"
        override fun getCommandWithArgs(): String = "$INSERT_PARAGRAPH <position>|end <text>"
    },
    INSERT_IMAGE {
        override fun toString(): String = "InsertImage"
        override fun getCommandWithArgs(): String = "$INSERT_IMAGE <position>|end <weight> <height> <path to file>"
    },
    SET_TITLE {
        override fun toString(): String = "SetTitle"
        override fun getCommandWithArgs(): String = "$SET_TITLE <new title>"
    },
    LIST {
        override fun toString(): String = "List"
        override fun getCommandWithArgs(): String = "$LIST"
    },
    REPLACE_TEXT {
        override fun toString(): String = "ReplaceText"
        override fun getCommandWithArgs(): String = "$REPLACE_TEXT <position> <new text>"
    },
    RESIZE_IMAGE {
        override fun toString(): String = "ReplaceImage"
        override fun getCommandWithArgs(): String = "$RESIZE_IMAGE <position> <weight> <height>"
    },
    DELETE_ITEM {
        override fun toString(): String = "DeleteItem"
        override fun getCommandWithArgs(): String = "$DELETE_ITEM <position>"
    },
    HELP {
        override fun toString(): String = "Help"
        override fun getCommandWithArgs(): String = "$HELP"
    },
    UNDO {
        override fun toString(): String = "Undo"
        override fun getCommandWithArgs(): String = "$UNDO"
    },
    REDO {
        override fun toString(): String = "Redo"
        override fun getCommandWithArgs(): String = "$REDO"
    },
    SAVE {
        override fun toString(): String = "Save"
        override fun getCommandWithArgs(): String = "$SAVE <path>"
    };

    companion object {
        fun getCommand(str: String): CommandType {
            values().forEach { if (str == it.toString()) return it }
            throw IllegalArgumentException("Not valid command: $str, use Help for show all commands")
        }
    }

    abstract fun getCommandWithArgs(): String
}