package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

open class FoundationOnActionFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationActionPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("OnAction", FoundationActionPackage) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationOnActionFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationActionPackage): FoundationOnActionFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationOnActionTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationOnActionFileManager(it) }
        }
    }
}