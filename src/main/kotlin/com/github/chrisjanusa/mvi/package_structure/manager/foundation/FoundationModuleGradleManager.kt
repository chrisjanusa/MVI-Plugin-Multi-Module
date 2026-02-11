package com.github.chrisjanusa.mvi.package_structure.manager.foundation

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationModuleGradleManager(file: VirtualFile) : ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", FoundationModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationModuleGradleManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationModule): FoundationModuleGradleManager? {
                return insertionPackage.createNewFileWithExtension(
                    NAME,
                    Extension.Kts,
                    FoundationModuleGradleTemplate(insertionPackage, NAME)
                        .createContent()
                )?.let { FoundationModuleGradleManager(it) }
        }
    }
}