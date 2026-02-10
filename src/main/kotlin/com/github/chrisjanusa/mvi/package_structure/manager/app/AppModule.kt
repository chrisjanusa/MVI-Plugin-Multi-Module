package com.github.chrisjanusa.mvi.package_structure.manager.app

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class AppModule(file: VirtualFile): ModuleManager(file) {
    override val projectPackage by lazy {
        ProjectPackage(file.parent)
    }

    val rootPackage by lazy {
        codePackageFile?.let { RootPackage(it) }
    }

    companion object : StaticChildInstanceCompanion("app", ProjectPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AppModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(ModuleGradleManager.Companion, RootPackage.Companion)
    }
}