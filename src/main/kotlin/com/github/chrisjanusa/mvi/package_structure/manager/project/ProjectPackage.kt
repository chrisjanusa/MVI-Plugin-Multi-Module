package com.github.chrisjanusa.mvi.package_structure.manager.project

import com.github.chrisjanusa.mvi.helper.file_helper.getProjectPackage
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.ParentInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.AppModule
import com.github.chrisjanusa.mvi.package_structure.manager.build_logic.BuildLogicPackage
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.core.CorePackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.FoundationModule
import com.github.chrisjanusa.mvi.package_structure.manager.project.gradle.GradlePackage
import com.github.chrisjanusa.mvi.package_structure.manager.project.library.LibraryManager
import com.github.chrisjanusa.mvi.package_structure.manager.project.manifest.ManifestManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.findFile

class ProjectPackage(file: VirtualFile): PackageManager(file) {
    val gradlePackage by lazy {
        file.findChild(GradlePackage.NAME)?.let { GradlePackage(it) }
    }
    val libs by lazy {
        gradlePackage?.libs
    }

    val projectGradle by lazy {
        file.findChild(ProjectGradleManager.NAME)?.let { ProjectGradleManager(it) }
    }

    val foundationModule by lazy {
        file.findChild(FoundationModule.NAME)?.let { FoundationModule(it) }
    }
    
    val commonPackage by lazy {
        file.findChild(CommonPackage.NAME)?.let { CommonPackage(it) }
    }

    val corePackage by lazy {
        file.findChild(CorePackage.NAME)?.let { CorePackage(it) }
    }

    val appModule by lazy {
        file.findChild(AppModule.NAME)?.let { AppModule(it) }
    }

    val manifest by lazy {
        file.findFile(ManifestManager.PATH_FROM_PROJECT)?.let { ManifestManager(it) }
    }

    val buildLogic by lazy {
        file.findChild(BuildLogicPackage.NAME)?.let { BuildLogicPackage(it) }
    }

    val basePackagePath by lazy {
        appModule?.moduleGradle?.getRootPackage()
    }

    val libraryManager by lazy {
        val libsManager = libs
        val projectGradleManager = projectGradle
        val moduleGradleManager = appModule?.moduleGradle
        if (libsManager == null || projectGradleManager == null || moduleGradleManager == null) return@lazy null
        LibraryManager(
            libsManager = libsManager,
            projectGradleManager = projectGradleManager,
            moduleGradleManager = moduleGradleManager
        )
    }

    companion object : ParentInstanceCompanion(GradlePackage) {
        override fun createInstance(virtualFile: VirtualFile) = ProjectPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                AppModule.Companion,
                FoundationModule.Companion,
                CorePackage.Companion,
                CommonPackage.Companion,
                ProjectGradleManager.Companion,
                BuildLogicPackage.Companion,
            )

        fun createNewInstance(project: Project): ProjectPackage? {
            return project.getProjectPackage()?.apply {
                FoundationModule.createNewInstance(this)
                CorePackage.createNewInstance(this)
                CommonPackage.createNewInstance(this)
                BuildLogicPackage.createNewInstance(this)
            }
        }
    }
}