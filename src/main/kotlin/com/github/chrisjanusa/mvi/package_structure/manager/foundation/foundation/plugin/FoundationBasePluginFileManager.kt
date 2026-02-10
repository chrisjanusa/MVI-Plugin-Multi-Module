package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationBasePluginFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationPluginPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("BasePlugin", FoundationPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationBasePluginFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationPluginPackage): FoundationBasePluginFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationBasePluginTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationBasePluginFileManager(it) }
        }
    }
}
