package com.github.chrisjanusa.mvi.package_structure.manager.core

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.core.nav.CoreNavModule
import com.github.chrisjanusa.mvi.package_structure.manager.core.remote.CoreRemoteModule
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.manager.nav.NavComponentFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.nav.NavComponentIdFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

class CorePackage(file: VirtualFile): PackageManager(file)  {
    override val projectPackage by lazy {
        ProjectPackage(file.parent)
    }

    val navModule by lazy {
        file.findChildFile(CoreNavModule.NAME)?.let { CoreNavModule(it) }
    }
    val remoteModule by lazy {
        file.findChildFile(CoreRemoteModule.NAME)?.let { CoreRemoteModule(it) }
    }

    fun createRemoteModule() {
        CoreRemoteModule.createNewInstance(this)
    }

    private fun createAllChildren() {
        CoreNavModule.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("core", ProjectPackage) {
        override fun createInstance(virtualFile: VirtualFile) = CorePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                CoreNavModule.Companion,
                CoreRemoteModule.Companion,
            )

        fun createNewInstance(insertionPackage: FoundationPackage): CorePackage? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CorePackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}