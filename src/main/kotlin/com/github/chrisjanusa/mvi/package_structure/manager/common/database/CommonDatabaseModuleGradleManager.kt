package com.github.chrisjanusa.mvi.package_structure.manager.common.database

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonDatabaseModuleGradleManager(file: VirtualFile): ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", CommonDatabaseModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonDatabaseModuleGradleManager(virtualFile)

        fun createNewInstance(insertionModule: CommonDatabaseModule): CommonDatabaseModuleGradleManager? {
            return insertionModule.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                CommonDatabaseModuleGradleTemplate(insertionModule).createContent()
            )?.let { CommonDatabaseModuleGradleManager(it) }
        }
    }
}
