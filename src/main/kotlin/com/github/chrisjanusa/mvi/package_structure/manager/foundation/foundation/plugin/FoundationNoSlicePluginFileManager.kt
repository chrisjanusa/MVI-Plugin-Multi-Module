package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationNoSlicePluginFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationPluginPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("NoSlicePlugin", FoundationPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationNoSlicePluginFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationPluginPackage): FoundationNoSlicePluginFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationNoSlicePluginTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationNoSlicePluginFileManager(it) }
        }
    }
}
