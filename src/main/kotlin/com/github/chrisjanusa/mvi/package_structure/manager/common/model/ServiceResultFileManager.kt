package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class ServiceResultFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("ServiceResult", CommonModelPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = ServiceResultFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonModelPackage): ServiceResultFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                ServiceResultTemplate(insertionPackage).createContent()
            )?.let { ServiceResultFileManager(it) }
        }
    }
}
