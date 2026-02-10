package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.intellij.openapi.vfs.VirtualFile

open class FoundationPluginStateFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationStatePackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("PluginState", FoundationStatePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationPluginStateFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationStatePackage): FoundationPluginStateFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationPluginStateTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationPluginStateFileManager(it) }
        }
    }
}