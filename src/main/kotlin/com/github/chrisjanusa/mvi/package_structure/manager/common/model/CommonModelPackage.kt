package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.intellij.openapi.vfs.VirtualFile

class CommonModelPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val commonPackage by lazy {
        CommonPackage(file.parent.parent.parent)
    }

    val dataError by lazy {
        file.findChildFile(DataErrorFileManager.NAME)?.let { DataErrorFileManager(it) }
    }

    val dataErrorToUiTextMapper by lazy {
        file.findChildFile(DataErrorToUiTextMapperFileManager.NAME)?.let { DataErrorToUiTextMapperFileManager(it) }
    }

    val error by lazy {
        file.findChildFile(ErrorFileManager.NAME)?.let { ErrorFileManager(it) }
    }

    val serviceResult by lazy {
        file.findChildFile(ServiceResultFileManager.NAME)?.let { ServiceResultFileManager(it) }
    }

    private fun createAllChildren() {
        DataErrorFileManager.createNewInstance(this)
        DataErrorToUiTextMapperFileManager.createNewInstance(this)
        ErrorFileManager.createNewInstance(this)
        ServiceResultFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("model", CommonModelModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonModelPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                DataErrorFileManager.Companion,
                DataErrorToUiTextMapperFileManager.Companion,
                ErrorFileManager.Companion,
                ServiceResultFileManager.Companion
            )

        fun createNewInstance(insertionModule: CommonModelModule): CommonModelPackage? {
            val packageManager = insertionModule.createCodePackage()?.let { CommonModelPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
