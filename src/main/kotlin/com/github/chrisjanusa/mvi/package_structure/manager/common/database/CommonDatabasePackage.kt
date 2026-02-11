package com.github.chrisjanusa.mvi.package_structure.manager.common.database

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.github.chrisjanusa.mvi.package_structure.manager.common.database.typeconverter.CommonTypeConverterPackage
import com.intellij.openapi.vfs.VirtualFile

class CommonDatabasePackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val commonPackage by lazy {
        CommonPackage(file.parent.parent.parent)
    }

    val typeConverterPackage by lazy {
        file.findChildFile(CommonTypeConverterPackage.NAME)?.let { CommonTypeConverterPackage(it) }
    }

    private fun createAllChildren() {
        CommonTypeConverterPackage.createNewInstance(this)
    }

    fun createTypeConverter(typeConverterName: String, type: String) {
        val typeConverterPackageInstance = typeConverterPackage ?: CommonTypeConverterPackage.createNewInstance(this)
        typeConverterPackageInstance?.createTypeConverter(typeConverterName, type)
    }

    companion object : StaticChildInstanceCompanion("database", CommonDatabaseModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonDatabasePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonTypeConverterPackage.Companion)

        fun createNewInstance(insertionModule: CommonDatabaseModule): CommonDatabasePackage? {
            // Note: Package path is com.example.common.database
            val packageManager = insertionModule.createCodePackage()?.let { CommonDatabasePackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
