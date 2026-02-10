package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AppApplicationFileManager(
    file: VirtualFile,
) : FileManager(file), AppNameProvider {
    override val appName: String by lazy {
        name.substringBefore(SUFFIX)
    }

    companion object : AppFileNameProvider("Application") {
        override fun createInstance(virtualFile: VirtualFile) = AppApplicationFileManager(virtualFile)
        fun createNewInstance(insertionPackage: AppPackage, appName: String): AppApplicationFileManager? {
            val fileName = getFileName(appName)
            return insertionPackage.createNewFile(
                fileName,
                AppApplicationTemplate(insertionPackage, fileName)
                    .createContent()
            )?.let { AppApplicationFileManager(it) }
        }
    }
}