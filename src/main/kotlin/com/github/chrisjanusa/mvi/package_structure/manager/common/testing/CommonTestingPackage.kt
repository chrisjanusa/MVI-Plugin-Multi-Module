package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.intellij.openapi.vfs.VirtualFile

class CommonTestingPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val commonPackage by lazy {
        CommonPackage(file.parent.parent.parent)
    }

    val metroTestRunner by lazy {
        file.findChildFile(MetroTestRunnerFileManager.NAME)?.let { MetroTestRunnerFileManager(it) }
    }

    val testApplication by lazy {
        file.findChildFile(TestApplicationFileManager.NAME)?.let { TestApplicationFileManager(it) }
    }

    private fun createAllChildren() {
        MetroTestRunnerFileManager.createNewInstance(this)
        TestApplicationFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("testing", CommonTestingModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonTestingPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                MetroTestRunnerFileManager.Companion,
                TestApplicationFileManager.Companion
            )

        fun createNewInstance(insertionModule: CommonTestingModule): CommonTestingPackage? {
            val packageManager = insertionModule.createCodePackage()?.let { CommonTestingPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
