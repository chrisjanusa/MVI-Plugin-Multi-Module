package com.github.chrisjanusa.mvi.package_structure.manager.app.root.theme

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
import com.intellij.openapi.vfs.VirtualFile

class ThemePackage(file: VirtualFile) : PackageManager(file) {
    val featureName = name.toPascalCase()

    val rootPackage by lazy {
        RootPackage(file.parent)
    }

    companion object : StaticChildInstanceCompanion("theme", RootPackage) {
        override fun createInstance(virtualFile: VirtualFile) = ThemePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf()
        fun createNewInstance(insertionPackage: RootPackage): ThemePackage? {
            val themePackage = insertionPackage.createNewDirectory(NAME)?.let { ThemePackage(it) }
            return themePackage
        }
    }
}