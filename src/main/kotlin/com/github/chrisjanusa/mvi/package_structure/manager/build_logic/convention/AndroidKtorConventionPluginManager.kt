package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AndroidKtorConventionPluginManager(file: VirtualFile) : FileManager(file) {

    companion object : StaticChildInstanceCompanion("AndroidKtorConventionPlugin", ConventionPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AndroidKtorConventionPluginManager(virtualFile)

        fun createNewInstance(packageManager: ConventionPluginPackage): AndroidKtorConventionPluginManager? {
            return packageManager.createNewFile(
                NAME,
                AndroidKtorConventionPluginTemplate(packageManager).createContent()
            )?.let { AndroidKtorConventionPluginManager(it) }
        }
    }
}
