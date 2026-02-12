package com.github.chrisjanusa.mvi.package_structure.manager.app.root.nav

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.app.AppPackage
import com.intellij.openapi.vfs.VirtualFile

class AppNavPackage(file: VirtualFile): PackageManager(file) {
    val appPackage by lazy {
        AppPackage(file.parent)
    }
    val rootPackage by lazy {
        appPackage.rootPackage
    }

    companion object : StaticChildInstanceCompanion("nav", RootPackage) {
        override fun createInstance(virtualFile: VirtualFile) = AppNavPackage(virtualFile)

        override val allChildrenInstanceCompanions = listOf(NavEffectFileManager.Companion)

        fun createNewInstance(insertionPackage: RootPackage): AppNavPackage? {
            val navPackage = insertionPackage.createNewDirectory(NAME)?.let { AppNavPackage(it) }
            return navPackage
        }
    }
}