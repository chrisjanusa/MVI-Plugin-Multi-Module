package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.viewmodel

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect.FoundationActionEffectFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect.FoundationStateEffectFileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationViewModelPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val foundationPackage by lazy {
        FoundationPackage(file.parent)
    }

    val noSlicePluginViewModel by lazy {
        file.findChildFile(FoundationNoSlicePluginViewModelFileManager.NAME)?.let { FoundationNoSlicePluginViewModelFileManager(it) }
    }

    val pluginViewModel by lazy {
        file.findChildFile(FoundationPluginViewModelFileManager.NAME)?.let { FoundationPluginViewModelFileManager(it) }
    }

    private fun createAllChildren() {
        FoundationNoSlicePluginViewModelFileManager.createNewInstance(this)
        FoundationPluginViewModelFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("viewmodel", FoundationPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationViewModelPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                FoundationNoSlicePluginViewModelFileManager.Companion,
                FoundationPluginViewModelFileManager.Companion
            )

        fun createNewInstance(insertionModule: FoundationPackage): FoundationViewModelPackage? {
            val packageManager = insertionModule.createNewDirectory(NAME)?.let { FoundationViewModelPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
