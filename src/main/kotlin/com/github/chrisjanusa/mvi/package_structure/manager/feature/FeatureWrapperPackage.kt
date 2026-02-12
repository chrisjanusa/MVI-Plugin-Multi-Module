package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.package_structure.instance_companion.ChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.intellij.openapi.vfs.VirtualFile

class FeatureWrapperPackage(file: VirtualFile) : PackageManager(file) {
    val featureName: String
        get() = file.name

    companion object : ChildInstanceCompanion(FeaturePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FeatureWrapperPackage(virtualFile)

        override val allChildrenInstanceCompanions = listOf(
                FeatureDomainModule.Companion,
                FeatureServiceModule.Companion,
                PluginWrapperPackage.Companion
            )

        fun createNewInstance(packageManager: FeaturePackage, featureName: String): FeatureWrapperPackage? {
            return packageManager.createNewDirectory(featureName)?.let { FeatureWrapperPackage(it) }
        }
    }
}
