package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class FeatureServiceModuleGradleManager(file: VirtualFile) : ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", FeatureServiceModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FeatureServiceModuleGradleManager(virtualFile)

        fun createNewInstance(insertionPackage: FeatureServiceModule): FeatureServiceModuleGradleManager? {
            return insertionPackage.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                FeatureServiceModuleGradleTemplate(insertionPackage).createContent()
            )?.let { FeatureServiceModuleGradleManager(it) }
        }
    }
}
