package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class UiTextFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("UiText", CommonUiPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = UiTextFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonUiPackage): UiTextFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                UiTextTemplate(insertionPackage).createContent()
            )?.let { UiTextFileManager(it) }
        }
    }
}
