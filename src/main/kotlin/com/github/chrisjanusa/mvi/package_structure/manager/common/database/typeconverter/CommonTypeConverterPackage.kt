package com.github.chrisjanusa.mvi.package_structure.manager.common.database.typeconverter

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.database.CommonDatabasePackage
import com.intellij.openapi.vfs.VirtualFile

class CommonTypeConverterPackage(file: VirtualFile): PackageManager(file) {
    val typeConverters by lazy {
        file.children.map {
            CommonTypeConverterFileManager(it)
        }
    }

    fun createTypeConverter(typeConverterName: String, type: String) {
        CommonTypeConverterFileManager.createNewInstance(this, typeConverterName, type)
    }

    private fun createAllChildren() {
    }

    companion object : StaticChildInstanceCompanion("typeconverter", CommonDatabasePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonTypeConverterPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CommonTypeConverterFileManager.Companion)

        fun createNewInstance(insertionPackage: CommonDatabasePackage): CommonTypeConverterPackage? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CommonTypeConverterPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
