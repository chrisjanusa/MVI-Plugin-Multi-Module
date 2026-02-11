package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CoreNavModuleGradleManager(file: VirtualFile) : ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", CoreNavModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreNavModuleGradleManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreNavModule): CoreNavModuleGradleManager? {
                return insertionPackage.createNewFileWithExtension(
                    NAME,
                    Extension.Kts,
                    CoreNavModuleGradleTemplate(insertionPackage, NAME)
                        .createContent()
                )?.let { CoreNavModuleGradleManager(it) }
        }
    }
}