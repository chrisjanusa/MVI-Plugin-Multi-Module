package com.github.chrisjanusa.mvi.package_structure.manager.build_logic

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.intellij.openapi.vfs.VirtualFile

import com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention.ConventionPluginPackage

class BuildLogicPackage(file: VirtualFile) : PackageManager(file) {

    val buildGradle by lazy {
        file.findChildFile(BuildLogicBuildGradleManager.NAME)?.let { BuildLogicBuildGradleManager(it) }
    }

    val settingsGradle by lazy {
        file.findChildFile(BuildLogicSettingsGradleManager.NAME)?.let { BuildLogicSettingsGradleManager(it) }
    }

    val conventionPluginPackage by lazy {
        file.findChildFile(SRC_PACKAGE_NAME)
            ?.findChildFile(MAIN_PACKAGE_NAME)
            ?.findChildFile(KOTLIN_PACKAGE_NAME)
            ?.let { ConventionPluginPackage(it) }
    }

    private fun createAllChildren() {
        BuildLogicBuildGradleManager.createNewInstance(this)
        BuildLogicSettingsGradleManager.createNewInstance(this)
        ConventionPluginPackage.createNewInstance(this)
    }
    
    fun createConventionPluginPackage(): VirtualFile? {
        return createNewDirectory(SRC_PACKAGE_NAME)
            ?.createNewDirectory(MAIN_PACKAGE_NAME)
            ?.createNewDirectory(KOTLIN_PACKAGE_NAME)
    }

    companion object : StaticChildInstanceCompanion("build-logic", ProjectPackage.Companion) {
        private const val SRC_PACKAGE_NAME = "src"
        private const val MAIN_PACKAGE_NAME = "main"
        private const val KOTLIN_PACKAGE_NAME = "kotlin"

        override fun createInstance(virtualFile: VirtualFile) = BuildLogicPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                BuildLogicBuildGradleManager.Companion,
                BuildLogicSettingsGradleManager.Companion,
                ConventionPluginPackage.Companion
            )

        fun createNewInstance(insertionPackage: ProjectPackage): BuildLogicPackage? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { BuildLogicPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
