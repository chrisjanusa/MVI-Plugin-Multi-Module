package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.hasPattern
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootDirectChild
import com.intellij.openapi.vfs.VirtualFile

class AppPackage(file: VirtualFile) : PackageManager(file), RootDirectChild {
    val appName by lazy {
        (activity ?: application ?: viewModel)?.appName
    }
    override val rootPackage by lazy {
        RootPackage(file.parent)
    }

    val activity by lazy {
        file.children.first { it.hasPattern("", AppActivityFileManager.SUFFIX) }?.let { AppActivityFileManager(it) }
    }
    val application by lazy {
        file.children.first { it.hasPattern("", AppApplicationFileManager.SUFFIX) }
            ?.let { AppApplicationFileManager(it) }
    }
    val viewModel by lazy {
        file.children.first { it.hasPattern("", AppViewModelFileManager.SUFFIX) }?.let { AppViewModelFileManager(it) }
    }

    private fun createAllChildren(appName: String) {
        AppViewModelFileManager.createNewInstance(this, appName)
        AppActivityFileManager.createNewInstance(this, appName)
        AppApplicationFileManager.createNewInstance(this, appName)
    }

    companion object : StaticChildInstanceCompanion("app", RootPackage) {
        override fun createInstance(virtualFile: VirtualFile) = AppPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                AppActivityFileManager,
                AppApplicationFileManager,
                AppViewModelFileManager
            )

        fun createNewInstance(insertionPackage: RootPackage, appName: String): AppPackage? {
            val appPackage = insertionPackage.createNewDirectory(NAME)?.let { AppPackage(it) }
            appPackage?.createAllChildren(appName)
            return appPackage
        }
    }
}