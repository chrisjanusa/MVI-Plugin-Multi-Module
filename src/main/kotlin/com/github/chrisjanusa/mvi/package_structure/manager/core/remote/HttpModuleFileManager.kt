package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class HttpModuleFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("HttpModule", CoreRemotePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = HttpModuleFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreRemotePackage): HttpModuleFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                HttpModuleTemplate(insertionPackage).createContent()
            )?.let { HttpModuleFileManager(it) }
        }
    }
}
