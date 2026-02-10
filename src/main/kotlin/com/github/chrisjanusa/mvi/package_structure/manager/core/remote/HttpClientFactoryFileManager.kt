package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class HttpClientFactoryFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("HttpClientFactory", CoreRemotePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = HttpClientFactoryFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreRemotePackage): HttpClientFactoryFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                HttpClientFactoryTemplate(insertionPackage).createContent()
            )?.let { HttpClientFactoryFileManager(it) }
        }
    }
}
