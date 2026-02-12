package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.intellij.openapi.vfs.VirtualFile

class FoundationStatePackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val foundationPackage by lazy {
        FoundationPackage(file.parent)
    }

    val noSlice by lazy {
        file.findChildFile(FoundationNoSliceFileManager.NAME)?.let { FoundationNoSliceFileManager(it) }
    }

    val noState by lazy {
        file.findChildFile(FoundationNoStateFileManager.NAME)?.let { FoundationNoStateFileManager(it) }
    }

    val pluginSlice by lazy {
        file.findChildFile(FoundationPluginSliceFileManager.NAME)?.let { FoundationPluginSliceFileManager(it) }
    }

    val pluginState by lazy {
        file.findChildFile(FoundationPluginStateFileManager.NAME)?.let { FoundationPluginStateFileManager(it) }
    }

    private fun createAllChildren() {
        FoundationPluginStateFileManager.createNewInstance(this)
        FoundationPluginSliceFileManager.createNewInstance(this)
        FoundationNoStateFileManager.createNewInstance(this)
        FoundationNoSliceFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("state", FoundationPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationStatePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                FoundationPluginStateFileManager.Companion,
                FoundationPluginSliceFileManager.Companion,
                FoundationNoStateFileManager.Companion,
                FoundationNoSliceFileManager.Companion,
            )

        fun createNewInstance(insertionModule: FoundationPackage): FoundationStatePackage? {
            val packageManager = insertionModule.createNewDirectory(NAME)?.let { FoundationStatePackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}