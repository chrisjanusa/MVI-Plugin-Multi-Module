package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

open class FoundationOnAppActionFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationActionPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("OnAppAction", FoundationActionPackage) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationOnAppActionFileManager(virtualFile)
        fun createNewInstance(insertionPackage: FoundationActionPackage): FoundationOnAppActionFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationOnAppActionTemplate(insertionPackage, NAME)
                    .createContent()
            )?.let { FoundationOnAppActionFileManager(it) }
        }
    }
}