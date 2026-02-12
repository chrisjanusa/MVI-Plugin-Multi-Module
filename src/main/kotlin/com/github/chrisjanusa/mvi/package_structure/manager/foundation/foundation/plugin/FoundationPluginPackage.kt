package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.intellij.openapi.vfs.VirtualFile

class FoundationPluginPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val foundationPackage by lazy {
        FoundationPackage(file.parent)
    }

    val basePlugin by lazy {
        file.findChildFile(FoundationBasePluginFileManager.NAME)?.let { FoundationBasePluginFileManager(it) }
    }

    val noSlicePlugin by lazy {
        file.findChildFile(FoundationNoSlicePluginFileManager.NAME)?.let { FoundationNoSlicePluginFileManager(it) }
    }

    val plugin by lazy {
        file.findChildFile(FoundationPluginFileManager.NAME)?.let { FoundationPluginFileManager(it) }
    }

    private fun createAllChildren() {
        FoundationBasePluginFileManager.createNewInstance(this)
        FoundationNoSlicePluginFileManager.createNewInstance(this)
        FoundationPluginFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("plugin", FoundationPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationPluginPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                FoundationBasePluginFileManager.Companion,
                FoundationNoSlicePluginFileManager.Companion,
                FoundationPluginFileManager.Companion
            )

        fun createNewInstance(insertionModule: FoundationPackage): FoundationPluginPackage? {
            val packageManager = insertionModule.createNewDirectory(NAME)?.let { FoundationPluginPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
