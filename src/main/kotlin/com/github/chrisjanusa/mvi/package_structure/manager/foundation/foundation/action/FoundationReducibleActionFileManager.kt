package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

open class FoundationReducibleActionFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationActionPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("ReducibleAction", FoundationActionPackage) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationReducibleActionFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationActionPackage): FoundationReducibleActionFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationReducibleActionTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationReducibleActionFileManager(it) }
        }
    }
}