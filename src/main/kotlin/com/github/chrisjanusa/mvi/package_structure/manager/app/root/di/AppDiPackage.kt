package com.github.chrisjanusa.mvi.package_structure.manager.app.root.di

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.app.AppPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

class AppDiPackage(file: VirtualFile): PackageManager(file), RootChild {
    val appPackage by lazy {
        AppPackage(file.parent)
    }
    override val rootPackage by lazy {
        appPackage.rootPackage
    }

    val localAppGraph by lazy {
        file.findChildFile(LocalAppGraphFileManager.NAME)?.let { LocalAppGraphFileManager(it) }
    }

    val metroAppGraph by lazy {
        file.findChildFile(MetroAppGraphFileManager.NAME)?.let { MetroAppGraphFileManager(it) }
    }

    val metroViewModelFactory by lazy {
        file.findChildFile(MetroViewModelFactoryFileManager.NAME)?.let { MetroViewModelFactoryFileManager(it) }
    }

    private fun createAllChildren() {
        MetroAppGraphFileManager.createNewInstance(this)
        MetroViewModelFactoryFileManager.createNewInstance(this)
        LocalAppGraphFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("di", RootPackage) {
        override fun createInstance(virtualFile: VirtualFile) = AppDiPackage(virtualFile)
        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(MetroAppGraphFileManager, MetroViewModelFactoryFileManager, LocalAppGraphFileManager)
        fun createNewInstance(insertionPackage: RootPackage): AppDiPackage? {
            val diPackage = insertionPackage.createNewDirectory(NAME)?.let { AppDiPackage(it) }
            diPackage?.createAllChildren()
            return diPackage
        }
    }
}