package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.intellij.openapi.vfs.VirtualFile

open class FoundationNoSliceFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationStatePackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("NoSlice", FoundationStatePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationNoSliceFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationStatePackage): FoundationNoSliceFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationNoSliceTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationNoSliceFileManager(it) }
        }
    }
}