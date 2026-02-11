package com.github.chrisjanusa.mvi.package_structure.manager.common.remote

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class HttpResponseHelperFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("HttpResponseHelper", CommonRemotePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = HttpResponseHelperFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonRemotePackage): HttpResponseHelperFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                HttpResponseHelperTemplate(insertionPackage).createContent()
            )?.let { HttpResponseHelperFileManager(it) }
        }
    }
}
