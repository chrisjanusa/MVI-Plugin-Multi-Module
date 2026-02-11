package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.intellij.openapi.vfs.VirtualFile

open class FoundationPluginSliceFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationStatePackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("PluginSlice", FoundationStatePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationPluginSliceFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationStatePackage): FoundationPluginSliceFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationPluginSliceTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationPluginSliceFileManager(it) }
        }
    }
}