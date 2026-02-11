package com.github.chrisjanusa.mvi.package_structure.manager.common.database.typeconverter

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticSuffixChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

open class CommonTypeConverterFileManager(file: VirtualFile) : FileManager(file) {
    val typeConverterPackage by lazy {
        CommonTypeConverterPackage(file.parent)
    }

    companion object : StaticSuffixChildInstanceCompanion("TypeConverter", CommonTypeConverterPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonTypeConverterFileManager(virtualFile)
        fun getFileName(typeConverterName: String) = "$typeConverterName$SUFFIX"
        fun createNewInstance(insertionPackage: CommonTypeConverterPackage, typeConverterName: String, type: String): CommonTypeConverterFileManager? {
            val name = getFileName(typeConverterName)
            return insertionPackage.createNewFile(
                name,
                CommonTypeConverterTemplate(insertionPackage, name, type)
                    .createContent()
            )?.let { CommonTypeConverterFileManager(it) }
        }
    }
}