package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.intellij.openapi.vfs.VirtualFile

class FeaturePackage(file: VirtualFile) : PackageManager(file) {
    fun getFeatureWrapperPackage(featureName: String): FeatureWrapperPackage? {
        return file.findChildFile(featureName)?.let { FeatureWrapperPackage(it) }
    }

    fun createFeatureWrapperPackage(featureName: String): FeatureWrapperPackage? {
        return FeatureWrapperPackage.createNewInstance(this, featureName)
    }

    companion object : StaticChildInstanceCompanion("feature", ProjectPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FeaturePackage(virtualFile)

        override val allChildrenInstanceCompanions = listOf(FeatureWrapperPackage.Companion, FeatureWrapperPackage.Companion)

        fun createNewInstance(packageManager: FeatureWrapperPackage): FeaturePackage? {
            return packageManager.createNewDirectory(NAME)?.let { FeaturePackage(it) }
        }
    }
}
