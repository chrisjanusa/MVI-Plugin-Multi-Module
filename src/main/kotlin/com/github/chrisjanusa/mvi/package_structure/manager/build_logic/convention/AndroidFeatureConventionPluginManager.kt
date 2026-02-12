package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AndroidFeatureConventionPluginManager(file: VirtualFile) : FileManager(file) {

    companion object : StaticChildInstanceCompanion("AndroidFeatureConventionPlugin", ConventionPluginPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AndroidFeatureConventionPluginManager(virtualFile)

        fun createNewInstance(packageManager: ConventionPluginPackage): AndroidFeatureConventionPluginManager? {
            return packageManager.createNewFile(
                NAME,
                AndroidFeatureConventionPluginTemplate(packageManager).createContent()
            )?.let { AndroidFeatureConventionPluginManager(it) }
        }
    }
}
