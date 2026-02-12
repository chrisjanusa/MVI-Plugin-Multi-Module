package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class AppActivityFileManager(
    file: VirtualFile,
) : FileManager(file), AppNameProvider {
    override val appName: String by lazy {
        name.substringBefore(SUFFIX)
    }

    companion object : AppFileNameProvider("Activity") {
        override fun createInstance(virtualFile: VirtualFile) = AppActivityFileManager(virtualFile)
        fun createNewInstance(insertionPackage: AppPackage, appName: String): AppActivityFileManager? {
            val fileName = getFileName(appName)
            val appActivity = insertionPackage.createNewFile(
                fileName,
                AppActivityTemplate(insertionPackage, fileName)
                    .createContent()
            )?.let { AppActivityFileManager(it) }
            // TODO: Check if we need this still
//            if (appActivity != null) {
//                ManagerProvider.setAppActivity(appActivity)
//            }
            return appActivity
        }
    }
}