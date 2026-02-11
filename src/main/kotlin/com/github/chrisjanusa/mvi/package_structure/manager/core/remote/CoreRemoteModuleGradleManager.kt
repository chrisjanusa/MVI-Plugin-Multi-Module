package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.core.nav.CoreNavModule
import com.github.chrisjanusa.mvi.package_structure.manager.core.nav.CoreNavModuleGradleTemplate
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CoreRemoteModuleGradleManager(file: VirtualFile) : ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", CoreRemoteModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreRemoteModuleGradleManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreRemoteModule): CoreRemoteModuleGradleManager? {
                return insertionPackage.createNewFileWithExtension(
                    NAME,
                    Extension.Kts,
                    CoreRemoteModuleGradleTemplate(insertionPackage, NAME)
                        .createContent()
                )?.let { CoreRemoteModuleGradleManager(it) }
        }
    }
}