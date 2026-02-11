package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonTestingModuleGradleManager(file: VirtualFile): ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", CommonTestingModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonTestingModuleGradleManager(virtualFile)

        fun createNewInstance(insertionModule: CommonTestingModule): CommonTestingModuleGradleManager? {
            return insertionModule.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                CommonTestingModuleGradleTemplate(insertionModule).createContent()
            )?.let { CommonTestingModuleGradleManager(it) }
        }
    }
}
