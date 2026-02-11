package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.common.ui.CommonUiModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.ui.CommonUiPackage
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonTestingModule(file: VirtualFile): ModuleManager(file) {
    val commonTestingPackage by lazy {
        codePackageFile?.let { CommonTestingPackage(it) }
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(CommonTestingModuleGradleManager.NAME, Extension.Kts)?.let { CommonTestingModuleGradleManager(it) }
    }

    private fun createAllChildren() {
        CommonTestingModuleGradleManager.createNewInstance(this)
        CommonTestingPackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("testing", CommonPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonTestingModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonTestingModuleGradleManager.Companion, CommonTestingPackage.Companion)

        fun createNewInstance(insertionPackage: CommonPackage): CommonTestingModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonTestingModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
