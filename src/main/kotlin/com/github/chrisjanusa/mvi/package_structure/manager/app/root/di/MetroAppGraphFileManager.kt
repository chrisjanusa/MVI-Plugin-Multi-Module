package com.github.chrisjanusa.mvi.package_structure.manager.app.root.di

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class MetroAppGraphFileManager(file: VirtualFile) : FileManager(file) {
    val diModule by lazy {
        AppDiPackage(file.parent)
    }
    val rootPackage by lazy {
        diModule.rootPackage
    }

    companion object : StaticChildInstanceCompanion("MetroAppGraph", AppDiPackage) {
        override fun createInstance(virtualFile: VirtualFile) = MetroAppGraphFileManager(virtualFile)
        fun createNewInstance(insertionPackage: AppDiPackage): MetroAppGraphFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                MetroAppGraphTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { MetroAppGraphFileManager(it) }
        }
    }
}