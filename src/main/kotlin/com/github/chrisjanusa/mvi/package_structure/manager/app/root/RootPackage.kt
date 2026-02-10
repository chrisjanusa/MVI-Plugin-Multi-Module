package com.github.chrisjanusa.mvi.package_structure.manager.app.root

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.ModuleChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.AppModule
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.app.AppPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.di.AppDiPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.nav.AppNavPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.theme.ThemePackage
import com.intellij.openapi.vfs.VirtualFile

class RootPackage(file: VirtualFile) : PackageManager(file) {
    val appPackage by lazy {
        file.findChild(AppPackage.NAME)?.let { AppPackage(it) }
    }
    val diPackage by lazy {
        file.findChild(AppDiPackage.NAME)?.let { AppDiPackage(it) }
    }
    val navPackage by lazy {
        file.findChild(AppNavPackage.NAME)?.let { AppNavPackage(it) }
    }
    val themePackage by lazy {
        file.findChild(ThemePackage.NAME)?.let { ThemePackage(it) }
    }
    val isInitialized by lazy {
        diPackage != null
    }

    fun createAllChildren(appName: String) {
        AppPackage.createNewInstance(this, appName)
        AppDiPackage.createNewInstance(this)
        AppNavPackage.createNewInstance(this)
        ThemePackage.createNewInstance(this)
    }

    companion object : ModuleChildInstanceCompanion(AppModule) {
        override fun createInstance(virtualFile: VirtualFile) = RootPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                AppPackage.Companion,
                AppDiPackage.Companion,
                AppNavPackage.Companion,
                ThemePackage.Companion,
            )
    }
}