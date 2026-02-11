package com.github.chrisjanusa.mvi.package_structure.manager.app.root.di

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

class MetroViewModelFactoryFileManager(file: VirtualFile) : FileManager(file), RootChild {
    val diModule by lazy {
        AppDiPackage(file.parent)
    }
    override val rootPackage by lazy {
        diModule.rootPackage
    }

    companion object : StaticChildInstanceCompanion("MetroViewModelFactory", AppDiPackage) {
        override fun createInstance(virtualFile: VirtualFile) = MetroViewModelFactoryFileManager(virtualFile)
        fun createNewInstance(insertionPackage: AppDiPackage): MetroViewModelFactoryFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                MetroViewModelFactoryTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { MetroViewModelFactoryFileManager(it) }
        }
    }
}