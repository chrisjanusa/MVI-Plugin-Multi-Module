package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.common.remote.CommonRemoteModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonModelModule(file: VirtualFile): ModuleManager(file) {
    val commonModelPackage by lazy {
        codePackageFile?.let { CommonModelPackage(it) }
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(CommonModelModuleGradleManager.NAME, Extension.Kts)?.let { CommonModelModuleGradleManager(it) }
    }

    private fun createAllChildren() {
        CommonModelModuleGradleManager.createNewInstance(this)
        CommonModelPackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("model", CommonPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonModelModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonModelModuleGradleManager.Companion, CommonModelPackage.Companion)

        fun createNewInstance(insertionPackage: CommonPackage): CommonModelModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonModelModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
