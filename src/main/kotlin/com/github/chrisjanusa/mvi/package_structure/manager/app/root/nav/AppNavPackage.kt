package com.github.chrisjanusa.mvi.package_structure.manager.app.root.nav

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.app.AppPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

class AppNavPackage(file: VirtualFile): PackageManager(file), RootChild {
    val appPackage by lazy {
        AppPackage(file.parent)
    }
    override val rootPackage by lazy {
        appPackage.rootPackage
    }

    private fun createAllChildren() {
        AppNavManagerFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("nav", RootPackage) {
        override fun createInstance(virtualFile: VirtualFile) = AppNavPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf()
        fun createNewInstance(insertionPackage: RootPackage): AppNavPackage? {
            val navPackage = insertionPackage.createNewDirectory(NAME)?.let { AppNavPackage(it) }
            return navPackage
        }
    }
}