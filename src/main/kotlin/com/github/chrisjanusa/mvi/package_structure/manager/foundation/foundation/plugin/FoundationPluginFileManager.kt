package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationPluginFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationPluginPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("Plugin", FoundationPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationPluginFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationPluginPackage): FoundationPluginFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationPluginTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationPluginFileManager(it) }
        }
    }
}
