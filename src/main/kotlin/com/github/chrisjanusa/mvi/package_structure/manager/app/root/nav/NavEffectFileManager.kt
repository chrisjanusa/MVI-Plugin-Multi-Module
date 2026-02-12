package com.github.chrisjanusa.mvi.package_structure.manager.app.root.nav

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticSuffixChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.di.AppDiPackage
import com.github.chrisjanusa.mvi.package_structure.manager.app.root.di.LocalAppGraphTemplate
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class NavEffectFileManager(file: VirtualFile) : FileManager(file) {
    val diModule by lazy {
        AppDiPackage(file.parent)
    }
    val rootPackage by lazy {
        diModule.rootPackage
    }

    companion object : StaticSuffixChildInstanceCompanion("NavEffect", AppNavPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = NavEffectFileManager(virtualFile)
        fun getFileName(effectName: String) = "$effectName$SUFFIX"
        fun createNewInstance(insertionPackage: AppDiPackage, effectName: String): NavEffectFileManager? {
            val name = getFileName(effectName)
            return insertionPackage.createNewFile(
                name,
                NavEffectFileTemplate(insertionPackage, name)
                    .createContent()
            )?.let { NavEffectFileManager(it) }
        }
    }
}