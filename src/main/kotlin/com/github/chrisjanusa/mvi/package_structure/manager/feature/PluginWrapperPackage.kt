package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.intellij.openapi.vfs.VirtualFile

class PluginWrapperPackage(file: VirtualFile) : PackageManager(file) {
    val featureWrapper by lazy {
        FeatureWrapperPackage(file.parent)
    }
    val featureName by lazy {
        featureWrapper.featureName
    }

    companion object : StaticChildInstanceCompanion("plugin", FeatureWrapperPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = PluginWrapperPackage(virtualFile)

//        override val allChildrenInstanceCompanions = listOf()

        fun createNewInstance(packageManager: FeatureWrapperPackage): PluginWrapperPackage? {
            return packageManager.createNewDirectory(NAME)?.let { PluginWrapperPackage(it) }
        }
    }
}
