package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.ViewModelFileManager
import com.intellij.openapi.vfs.VirtualFile

class AppViewModelFileManager(
    file: VirtualFile,
) : ViewModelFileManager(file), AppNameProvider {
    override val appName: String by lazy {
        name.substringBefore(SUFFIX)
    }

    companion object : AppFileNameProvider(SUFFIX) {
        override fun createInstance(virtualFile: VirtualFile) = AppViewModelFileManager(virtualFile)
        fun createNewInstance(insertionPackage: AppPackage, appName: String): AppViewModelFileManager? {
            val fileName = getFileName(appName)
            return insertionPackage.createNewFile(
                fileName,
                AppViewModelTemplate(insertionPackage, fileName)
                    .createContent()
            )?.let { AppViewModelFileManager(it) }
        }
    }
}