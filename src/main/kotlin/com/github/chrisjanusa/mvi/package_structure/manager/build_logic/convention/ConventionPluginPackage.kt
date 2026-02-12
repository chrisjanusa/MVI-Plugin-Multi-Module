package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.ParentInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.build_logic.BuildLogicPackage
import com.intellij.openapi.vfs.VirtualFile

class ConventionPluginPackage(file: VirtualFile) : PackageManager(file) {
    val buildLogicPackage by lazy {
        BuildLogicPackage(file.parent.parent.parent)
    }

    val androidLibrary by lazy {
        file.findChildFile(AndroidLibraryConventionPluginManager.NAME)?.let { AndroidLibraryConventionPluginManager(it) }
    }

    val androidCompose by lazy {
        file.findChildFile(AndroidComposeConventionPluginManager.NAME)?.let { AndroidComposeConventionPluginManager(it) }
    }

    val androidRoom by lazy {
        file.findChildFile(AndroidRoomConventionPluginManager.NAME)?.let { AndroidRoomConventionPluginManager(it) }
    }

    val androidMetro by lazy {
        file.findChildFile(AndroidMetroConventionPluginManager.NAME)?.let { AndroidMetroConventionPluginManager(it) }
    }

    val androidKtor by lazy {
        file.findChildFile(AndroidKtorConventionPluginManager.NAME)?.let { AndroidKtorConventionPluginManager(it) }
    }

    val androidFeature by lazy {
        file.findChildFile(AndroidFeatureConventionPluginManager.NAME)?.let { AndroidFeatureConventionPluginManager(it) }
    }

    fun createRoomConventionPlugin() = AndroidRoomConventionPluginManager.createNewInstance(this)

    fun createKtorConventionPlugin() = AndroidKtorConventionPluginManager.createNewInstance(this)

    private fun createAllChildren() {
        AndroidLibraryConventionPluginManager.createNewInstance(this)
        AndroidComposeConventionPluginManager.createNewInstance(this)
        AndroidMetroConventionPluginManager.createNewInstance(this)
        AndroidFeatureConventionPluginManager.createNewInstance(this)
    }

    companion object : ParentInstanceCompanion(
        AndroidLibraryConventionPluginManager.Companion,
        AndroidComposeConventionPluginManager.Companion,
        AndroidRoomConventionPluginManager.Companion,
        AndroidMetroConventionPluginManager.Companion,
        AndroidKtorConventionPluginManager.Companion,
        AndroidFeatureConventionPluginManager.Companion
    ) {
        override fun createInstance(virtualFile: VirtualFile) = ConventionPluginPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                AndroidLibraryConventionPluginManager.Companion,
                AndroidComposeConventionPluginManager.Companion,
                AndroidRoomConventionPluginManager.Companion,
                AndroidMetroConventionPluginManager.Companion,
                AndroidKtorConventionPluginManager.Companion,
                AndroidFeatureConventionPluginManager.Companion
            )

        fun createNewInstance(insertionPackage: BuildLogicPackage): ConventionPluginPackage? {
            return insertionPackage.createConventionPluginPackage()?.let { ConventionPluginPackage(it) }
                ?.apply { createAllChildren() }
        }
    }
}
