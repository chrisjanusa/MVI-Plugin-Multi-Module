package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.viewmodel

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationPluginViewModelFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationViewModelPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("PluginViewModel", FoundationViewModelPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationPluginViewModelFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationViewModelPackage): FoundationPluginViewModelFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationPluginViewModelTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationPluginViewModelFileManager(it) }
        }
    }
}
