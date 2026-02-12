package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AndroidLibraryConventionPluginManager(file: VirtualFile) : FileManager(file) {

    companion object : StaticChildInstanceCompanion("AndroidLibraryConventionPlugin", ConventionPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AndroidLibraryConventionPluginManager(virtualFile)

        fun createNewInstance(packageManager: ConventionPluginPackage): AndroidLibraryConventionPluginManager? {
            return packageManager.createNewFile(
                NAME,
                AndroidLibraryConventionPluginTemplate(packageManager).createContent()
            )?.let { AndroidLibraryConventionPluginManager(it) }
        }
    }
}
