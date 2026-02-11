package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class CoreNavActionFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("CoreNavAction", CoreNavPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreNavActionFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreNavPackage): CoreNavActionFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                CoreNavActionTemplate(insertionPackage).createContent()
            )?.let { CoreNavActionFileManager(it) }
        }
    }
}
