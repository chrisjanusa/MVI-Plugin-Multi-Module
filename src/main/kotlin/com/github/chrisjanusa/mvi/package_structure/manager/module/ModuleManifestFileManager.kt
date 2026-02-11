package com.github.chrisjanusa.mvi.package_structure.manager.module

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state.FoundationStatePackage
import com.intellij.openapi.vfs.VirtualFile

open class ModuleManifestFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticInstanceCompanion("AndroidManifest") {
        override fun createInstance(virtualFile: VirtualFile) = ModuleManifestFileManager(virtualFile)
        fun createNewInstance(insertionPackage: VirtualFile): ModuleManifestFileManager? {
            return insertionPackage.createNewFileWithExtension(
                NAME,
                Extension.Xml,
                "\"<?xml version=\\\"1.0\\\" encoding=\\\"utf-8\\\"?>\\n\" +\n" +
                "                \"<manifest xmlns:android=\\\"http://schemas.android.com/apk/res/android\\\">\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"</manifest>\""
            )?.let { ModuleManifestFileManager(it) }
        }
    }
}