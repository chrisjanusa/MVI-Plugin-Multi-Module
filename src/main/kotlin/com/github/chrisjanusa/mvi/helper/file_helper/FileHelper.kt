package com.github.chrisjanusa.mvi.helper.file_helper

import com.intellij.openapi.vfs.VirtualFile

fun VirtualFile.isValidFile() = name != "/"
fun VirtualFile.findChildFile(name: String) = findChildFileWithExtension(name, Extension.Kotlin)
internal fun VirtualFile.findChildFileWithExtension(name: String, extension: Extension) = this.findChild("$name.${extension.extension}")