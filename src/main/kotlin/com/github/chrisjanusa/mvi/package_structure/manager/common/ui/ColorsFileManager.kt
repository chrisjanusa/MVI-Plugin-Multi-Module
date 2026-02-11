package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class ColorsFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("Colors", CommonUiPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = ColorsFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonUiPackage): ColorsFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                ColorsTemplate(insertionPackage).createContent()
            )?.let { ColorsFileManager(it) }
        }
    }
}
