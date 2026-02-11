package com.github.chrisjanusa.mvi.package_structure.manager.common.remote

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.common.testing.CommonTestingModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonRemoteModule(file: VirtualFile): ModuleManager(file) {
    val commonRemotePackage by lazy {
        codePackageFile?.let { CommonRemotePackage(it) }
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(CommonRemoteModuleGradleManager.NAME, Extension.Kts)?.let { CommonRemoteModuleGradleManager(it) }
    }

    private fun createAllChildren() {
        CommonRemoteModuleGradleManager.createNewInstance(this)
        CommonRemotePackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("remote", CommonPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonRemoteModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonRemoteModuleGradleManager.Companion, CommonRemotePackage.Companion)

        fun createNewInstance(insertionPackage: CommonPackage): CommonRemoteModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonRemoteModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
