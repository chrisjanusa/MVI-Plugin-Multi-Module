package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.core.CorePackage
import com.intellij.openapi.vfs.VirtualFile

class CoreRemotePackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()

    private fun createAllChildren() {
        HttpClientFactoryFileManager.createNewInstance(this)
        HttpModuleFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("remote", CorePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreRemotePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                HttpClientFactoryFileManager.Companion,
                HttpModuleFileManager.Companion
            )

        fun createNewInstance(insertionModule: CoreRemoteModule): CoreRemotePackage? {
            val packageManager = insertionModule.createCodePackage()?.let { CoreRemotePackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
