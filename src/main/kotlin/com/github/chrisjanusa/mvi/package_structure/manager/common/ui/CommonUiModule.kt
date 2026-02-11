package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.core.remote.CoreRemoteModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonUiModule(file: VirtualFile): ModuleManager(file) {
    val commonUiPackage by lazy {
        codePackageFile?.let { CommonUiPackage(it) }
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(CommonUiModuleGradleManager.NAME, Extension.Kts)?.let { CommonUiModuleGradleManager(it) }
    }

    private fun createAllChildren() {
        CommonUiModuleGradleManager.createNewInstance(this)
        CommonUiPackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("ui", CommonPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonUiModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonUiModuleGradleManager.Companion, CommonUiPackage.Companion)

        fun createNewInstance(insertionPackage: CommonPackage): CommonUiModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonUiModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
