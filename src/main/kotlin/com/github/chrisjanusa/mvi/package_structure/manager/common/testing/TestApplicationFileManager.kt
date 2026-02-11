package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class TestApplicationFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("TestApplication", CommonTestingPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = TestApplicationFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CommonTestingPackage): TestApplicationFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                TestApplicationTemplate(insertionPackage).createContent()
            )?.let { TestApplicationFileManager(it) }
        }
    }
}
