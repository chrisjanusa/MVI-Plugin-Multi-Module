package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class MetroTestRunnerFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("MetroTestRunner", CommonTestingPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = MetroTestRunnerFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonTestingPackage): MetroTestRunnerFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                MetroTestRunnerTemplate(insertionPackage).createContent()
            )?.let { MetroTestRunnerFileManager(it) }
        }
    }
}
