package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class FeatureServiceModule(file: VirtualFile) : ModuleManager(file) {
    val featureWrapper by lazy {
        FeatureWrapperPackage(file.parent)
    }
    val featureName by lazy {
        featureWrapper.featureName
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(FeatureServiceModuleGradleManager.NAME, Extension.Kts)?.let { FeatureServiceModuleGradleManager(it) }
    }

    companion object : StaticChildInstanceCompanion("service", FeatureWrapperPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FeatureServiceModule(virtualFile)

        override val allChildrenInstanceCompanions = listOf(FeatureServiceModuleGradleManager.Companion)

        fun createNewInstance(packageManager: FeatureWrapperPackage): FeatureServiceModule? {
            return packageManager.createNewDirectory(NAME)?.let { FeatureServiceModule(it) }
        }
    }
}
