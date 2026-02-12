package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.intellij.openapi.vfs.VirtualFile

open class FoundationActionFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationActionPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("Action", FoundationActionPackage) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationActionFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationActionPackage): FoundationActionFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationActionTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationActionFileManager(it) }
        }
    }
}