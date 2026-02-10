package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.core.CorePackage
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class CoreRemoteModule(file: VirtualFile): ModuleManager(file) {

    val coreRemotePackage by lazy {
        codePackageFile?.let { CoreRemotePackage(it) }
    }

    private fun createAllChildren() {
        CoreRemoteModuleGradleManager.createNewInstance(this)
        CoreRemotePackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("remote", CorePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreRemoteModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CoreRemoteModuleGradleManager.Companion)

        fun createNewInstance(insertionPackage: CorePackage): CoreRemoteModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CoreRemoteModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}