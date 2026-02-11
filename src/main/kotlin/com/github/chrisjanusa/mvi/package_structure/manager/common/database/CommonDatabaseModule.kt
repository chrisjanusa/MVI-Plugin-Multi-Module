package com.github.chrisjanusa.mvi.package_structure.manager.common.database

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.common.remote.CommonRemoteModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.testing.CommonTestingPackage
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.intellij.openapi.vfs.VirtualFile

class CommonDatabaseModule(file: VirtualFile): ModuleManager(file) {
    val commonDatabasePackage by lazy {
        codePackageFile?.let { CommonDatabasePackage(it) }
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(CommonDatabaseModuleGradleManager.NAME, Extension.Kts)?.let { CommonDatabaseModuleGradleManager(it) }
    }

    private fun createAllChildren() {
        CommonDatabaseModuleGradleManager.createNewInstance(this)
        CommonDatabasePackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("database", CommonPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonDatabaseModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonDatabaseModuleGradleManager.Companion, CommonTestingPackage.Companion)

        fun createNewInstance(insertionPackage: CommonPackage): CommonDatabaseModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonDatabaseModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
