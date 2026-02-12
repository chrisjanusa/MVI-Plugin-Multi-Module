package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AndroidMetroConventionPluginManager(file: VirtualFile) : FileManager(file) {

    companion object : StaticChildInstanceCompanion("AndroidMetroConventionPlugin", ConventionPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AndroidMetroConventionPluginManager(virtualFile)

        fun createNewInstance(packageManager: ConventionPluginPackage): AndroidMetroConventionPluginManager? {
            return packageManager.createNewFile(
                NAME,
                AndroidMetroConventionPluginTemplate(packageManager).createContent()
            )?.let { AndroidMetroConventionPluginManager(it) }
        }
    }
}
