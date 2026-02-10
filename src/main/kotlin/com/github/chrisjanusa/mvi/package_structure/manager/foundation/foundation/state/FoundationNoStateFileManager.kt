package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.intellij.openapi.vfs.VirtualFile

open class FoundationNoStateFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationStatePackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("NoState", FoundationStatePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationNoStateFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationStatePackage): FoundationNoStateFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationNoStateTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationNoStateFileManager(it) }
        }
    }
}