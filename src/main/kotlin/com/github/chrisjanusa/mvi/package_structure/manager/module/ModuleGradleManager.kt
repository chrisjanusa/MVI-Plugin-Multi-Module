package com.github.chrisjanusa.mvi.package_structure.manager.module

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticExcludeChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.github.chrisjanusa.mvi.package_structure.manager.project.library.LibraryPlugin
import com.intellij.openapi.vfs.VirtualFile

abstract class ModuleGradleManager(
    file: VirtualFile,
) : FileManager(file) {
    fun addPluginLibraryToGradle(plugin: LibraryPlugin, libraryGroupName: String) {
        val moduleDocumentText = documentLines.toMutableList()
        val pluginDep = "alias(libs.plugins.$libraryGroupName.${plugin.pluginName})"
        if (!moduleDocumentText.any { it.contains(pluginDep) }) {
            run {
                moduleDocumentText.forEachIndexed { index, line ->
                    if (line.contains("plugins {")) {
                        moduleDocumentText.add(
                            index + 1,
                            "    $pluginDep"
                        )
                        return@run
                    }
                }
            }
            documentLines = moduleDocumentText
        }
    }

    fun addLibraryToGradle(pluginDep: String) {
        val moduleDocumentText = documentLines.toMutableList()
        if (!moduleDocumentText.any { it.contains(pluginDep) }) {
            run {
                moduleDocumentText.forEachIndexed { index, line ->
                    if (line.contains("dependencies {")) {
                        moduleDocumentText.add(
                            index + 1,
                            "    $pluginDep"
                        )
                        return@run
                    }
                }
            }
            documentLines = moduleDocumentText
        }
    }

    fun getRootPackage(): String {
        return documentLines
            .first { it.contains("namespace") }
            .substringAfter("\"")
            .substringBeforeLast("\"")
    }

//    companion object : StaticExcludeChildInstanceCompanion("build.gradle.kts", ProjectPackage.Companion) {
//        override fun createInstance(virtualFile: VirtualFile) = ModuleGradleManager(virtualFile)
//    }
}