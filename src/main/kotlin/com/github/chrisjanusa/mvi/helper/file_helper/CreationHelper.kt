package com.github.chrisjanusa.mvi.helper.file_helper

import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.ThrowableRunnable

internal fun VirtualFile.createNewDirectory(name: String): VirtualFile? {
    val existingDirectory = findChild(name)
    if (existingDirectory != null) return existingDirectory

    var dirFile: VirtualFile? = null
    WriteAction.runAndWait(ThrowableRunnable {
        dirFile = createChildDirectory(this, name)
    })
    return dirFile
}