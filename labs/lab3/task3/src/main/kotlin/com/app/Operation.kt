package com.app

enum class Operation(operationName: String) {
    ENCRYPT("--encrypt"),
    DECRYPT ("--decrypt"),
    COMPRESS("--compress"),
    DECOMPRESS("--decompress");
}