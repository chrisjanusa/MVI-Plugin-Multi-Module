package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonUiModuleGradleManager(file: VirtualFile): ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", CommonUiModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonUiModuleGradleManager(virtualFile)

        fun createNewInstance(insertionModule: CommonUiModule): CommonUiModuleGradleManager? {
            return insertionModule.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                CommonUiModuleGradleTemplate(insertionModule).createContent()
            )?.let { CommonUiModuleGradleManager(it) }
        }
    }
}
