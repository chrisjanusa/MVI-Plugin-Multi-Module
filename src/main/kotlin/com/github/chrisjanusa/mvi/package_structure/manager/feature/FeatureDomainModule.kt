package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class FeatureDomainModule(file: VirtualFile) : ModuleManager(file) {
    val featureWrapper by lazy {
        FeatureWrapperPackage(file.parent)
    }
    val featureName by lazy {
        featureWrapper.featureName
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(FeatureDomainModuleGradleManager.NAME, Extension.Kts)?.let { FeatureDomainModuleGradleManager(it) }
    }

    companion object : StaticChildInstanceCompanion("domain", FeatureWrapperPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FeatureDomainModule(virtualFile)

        override val allChildrenInstanceCompanions = listOf(FeatureDomainModuleGradleManager.Companion)

        fun createNewInstance(packageManager: FeatureWrapperPackage): FeatureDomainModule? {
            return packageManager.createNewDirectory(NAME)?.let { FeatureDomainModule(it) }
        }
    }
}
