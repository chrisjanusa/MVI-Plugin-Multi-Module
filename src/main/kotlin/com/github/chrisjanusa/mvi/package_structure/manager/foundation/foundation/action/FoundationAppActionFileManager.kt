package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.intellij.openapi.vfs.VirtualFile

open class FoundationAppActionFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationActionPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("AppAction", FoundationActionPackage) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationAppActionFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationActionPackage): FoundationAppActionFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationAppActionTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationAppActionFileManager(it) }
        }
    }
}