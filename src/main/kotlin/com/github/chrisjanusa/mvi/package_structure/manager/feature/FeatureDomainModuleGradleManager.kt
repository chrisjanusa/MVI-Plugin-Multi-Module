package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class FeatureDomainModuleGradleManager(file: VirtualFile) : ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", FeatureDomainModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FeatureDomainModuleGradleManager(virtualFile)

        fun createNewInstance(insertionPackage: FeatureDomainModule): FeatureDomainModuleGradleManager? {
            return insertionPackage.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                FeatureDomainModuleGradleTemplate(insertionPackage).createContent()
            )?.let { FeatureDomainModuleGradleManager(it) }
        }
    }
}
