package com.github.chrisjanusa.mvi.package_structure.manager.common

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.database.CommonDatabaseModule
import com.github.chrisjanusa.mvi.package_structure.manager.common.model.CommonModelModule
import com.github.chrisjanusa.mvi.package_structure.manager.common.remote.CommonRemoteModule
import com.github.chrisjanusa.mvi.package_structure.manager.common.testing.CommonTestingModule
import com.github.chrisjanusa.mvi.package_structure.manager.common.ui.CommonUiModule
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.intellij.openapi.vfs.VirtualFile

class CommonPackage(file: VirtualFile): PackageManager(file) {
    override val projectPackage by lazy {
        ProjectPackage(file.parent)
    }

    val modelModule by lazy {
        file.findChildFile(CommonModelModule.NAME)?.let { CommonModelModule(it) }
    }
    val databaseModule by lazy {
        file.findChildFile(CommonDatabaseModule.NAME)?.let { CommonDatabaseModule(it) }
    }
    val remoteModule by lazy {
        file.findChildFile(CommonRemoteModule.NAME)?.let { CommonRemoteModule(it) }
    }
    val uiModule by lazy {
        file.findChildFile(CommonUiModule.NAME)?.let { CommonUiModule(it) }
    }
    val testingModule by lazy {
        file.findChildFile(CommonTestingModule.NAME)?.let { CommonTestingModule(it) }
    }

    fun createDatabase() = CommonDatabaseModule.createNewInstance(this)

    fun createRemote() = CommonRemoteModule.createNewInstance(this)

    fun createModel() = CommonUiModule.createNewInstance(this)

    private fun createAllChildren() {
        CommonModelModule.createNewInstance(this)
        CommonTestingModule.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("common", ProjectPackage) {
        override fun createInstance(virtualFile: VirtualFile) = CommonPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                CommonModelModule.Companion,
                CommonDatabaseModule.Companion,
                CommonRemoteModule.Companion,
                CommonUiModule.Companion,
                CommonTestingModule.Companion
            )

        fun createNewInstance(insertionPackage: ProjectPackage): CommonPackage? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
