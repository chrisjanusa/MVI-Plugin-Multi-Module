package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class DataErrorToUiTextMapperFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("DataErrorToUiTextMapper", CommonModelPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = DataErrorToUiTextMapperFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonModelPackage): DataErrorToUiTextMapperFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                DataErrorToUiTextMapperTemplate(insertionPackage).createContent()
            )?.let { DataErrorToUiTextMapperFileManager(it) }
        }
    }
}
