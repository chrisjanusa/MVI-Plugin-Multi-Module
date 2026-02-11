package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonModelModuleGradleManager(file: VirtualFile): ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", CommonModelModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonModelModuleGradleManager(virtualFile)

        fun createNewInstance(insertionModule: CommonModelModule): CommonModelModuleGradleManager? {
            return insertionModule.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                CommonModelModuleGradleTemplate(insertionModule).createContent()
            )?.let { CommonModelModuleGradleManager(it) }
        }
    }
}
