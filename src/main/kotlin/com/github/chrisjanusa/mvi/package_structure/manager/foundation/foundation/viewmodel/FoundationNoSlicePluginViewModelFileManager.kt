package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.viewmodel

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationNoSlicePluginViewModelFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationViewModelPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("NoSlicePluginViewModel", FoundationViewModelPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationNoSlicePluginViewModelFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationViewModelPackage): FoundationNoSlicePluginViewModelFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationNoSlicePluginViewModelTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationNoSlicePluginViewModelFileManager(it) }
        }
    }
}
