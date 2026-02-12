package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AndroidComposeConventionPluginManager(file: VirtualFile) : FileManager(file) {

    companion object : StaticChildInstanceCompanion("AndroidComposeConventionPlugin", ConventionPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AndroidComposeConventionPluginManager(virtualFile)

        fun createNewInstance(packageManager: ConventionPluginPackage): AndroidComposeConventionPluginManager? {
             return packageManager.createNewFile(
                NAME,
                AndroidComposeConventionPluginTemplate(packageManager).createContent()
            )?.let { AndroidComposeConventionPluginManager(it) }
        }
    }
}
