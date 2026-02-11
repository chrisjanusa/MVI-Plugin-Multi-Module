package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class BackButtonFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("BackButton", CommonUiPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = BackButtonFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonUiPackage): BackButtonFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                BackButtonTemplate(insertionPackage).createContent()
            )?.let { BackButtonFileManager(it) }
        }
    }
}
