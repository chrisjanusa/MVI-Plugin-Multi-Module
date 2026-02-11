package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class DataErrorFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("DataError", CommonModelPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = DataErrorFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonModelPackage): DataErrorFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                DataErrorTemplate(insertionPackage).createContent()
            )?.let { DataErrorFileManager(it) }
        }
    }
}
