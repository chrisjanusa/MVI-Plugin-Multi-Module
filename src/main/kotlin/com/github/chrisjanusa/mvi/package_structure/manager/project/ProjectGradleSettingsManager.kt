package com.github.chrisjanusa.mvi.package_structure.manager.project

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.project.library.LibraryPlugin
import com.intellij.openapi.vfs.VirtualFile

class ProjectGradleSettingsManager(
    file: VirtualFile,
) : FileManager(file) {
    fun addModule(module: String) {
        val pluginDep = "include(\"$module\")"
        val projectDocumentText = documentLines.toMutableList()
        projectDocumentText.add(pluginDep)
        documentLines = projectDocumentText
    }

    companion object : StaticChildInstanceCompanion("settings.gradle.kts", ProjectPackage) {
        override fun createInstance(virtualFile: VirtualFile) = ProjectGradleSettingsManager(virtualFile)
    }
}