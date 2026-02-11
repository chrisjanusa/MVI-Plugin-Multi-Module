package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class NavComponentIdFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("NavComponentId", CoreNavPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = NavComponentIdFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreNavPackage): NavComponentIdFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                NavComponentIdTemplate(insertionPackage).createContent()
            )?.let { NavComponentIdFileManager(it) }
        }
    }
}
