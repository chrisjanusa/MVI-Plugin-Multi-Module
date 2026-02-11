package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class ErrorFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("Error", CommonModelPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = ErrorFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonModelPackage): ErrorFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                ErrorTemplate(insertionPackage).createContent()
            )?.let { ErrorFileManager(it) }
        }
    }
}
