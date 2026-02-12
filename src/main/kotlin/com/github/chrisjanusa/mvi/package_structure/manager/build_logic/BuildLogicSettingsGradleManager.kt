package com.github.chrisjanusa.mvi.package_structure.manager.build_logic

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class BuildLogicSettingsGradleManager(file: VirtualFile) : FileManager(file) {

    companion object : StaticChildInstanceCompanion("settings.gradle", BuildLogicPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = BuildLogicSettingsGradleManager(virtualFile)

        fun createNewInstance(packageManager: BuildLogicPackage): BuildLogicSettingsGradleManager? {
            return packageManager.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                BuildLogicSettingsGradleTemplate(packageManager).createContent()
            )?.let { BuildLogicSettingsGradleManager(it) }
        }
    }
}
