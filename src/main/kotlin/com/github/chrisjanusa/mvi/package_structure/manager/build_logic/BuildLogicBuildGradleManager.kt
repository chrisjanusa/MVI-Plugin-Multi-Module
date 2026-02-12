package com.github.chrisjanusa.mvi.package_structure.manager.build_logic

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class BuildLogicBuildGradleManager(file: VirtualFile) : FileManager(file) {
    val buildLogicPackage by lazy {
        BuildLogicPackage(file.parent)
    }
    fun addConventionPlugin(pluginIdSuffix: String, implementationClass: String) {
        val basePackage = buildLogicPackage.projectPackage?.basePackagePath ?: return
        if (documentText.contains("$basePackage.$pluginIdSuffix")) {
            return
        }

        val newPluginEntry = """
        val $pluginIdSuffix by creating {
            id = "$basePackage.$pluginIdSuffix"
            implementationClass = "$implementationClass"
        }
        """.trimIndent() + "\n"

        addAfterFirst(newPluginEntry) {
            it.contains("by creating {")
        }

        writeToDisk()
    }

    companion object : StaticChildInstanceCompanion("build.gradle", BuildLogicPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = BuildLogicBuildGradleManager(virtualFile)

        fun createNewInstance(packageManager: BuildLogicPackage): BuildLogicBuildGradleManager? {
            return packageManager.createNewFileWithExtension(
                NAME,
                Extension.Kts,
                BuildLogicBuildGradleTemplate(packageManager).createContent()
            )?.let { BuildLogicBuildGradleManager(it) }
        }
    }
}
