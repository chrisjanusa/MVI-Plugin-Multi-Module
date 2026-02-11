package com.github.chrisjanusa.mvi.package_structure.manager.foundation

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.intellij.openapi.vfs.VirtualFile

class FoundationModule(file: VirtualFile): ModuleManager(file) {
    override val projectPackage by lazy {
        ProjectPackage(file.parent)
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(FoundationModuleGradleManager.NAME, Extension.Kts)?.let { FoundationModuleGradleManager(it) }
    }

    val foundationPackage by lazy {
        codePackageFile?.let { FoundationPackage(it) }
    }

    private fun createAllChildren() {
        FoundationModuleGradleManager.createNewInstance(this)
        FoundationPackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("foundation", ProjectPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(FoundationModuleGradleManager.Companion, FoundationPackage.Companion)

        fun createNewInstance(insertionPackage: ProjectPackage): FoundationModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { FoundationModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}