package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.intellij.openapi.vfs.VirtualFile

class CommonUiPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val commonPackage by lazy {
        CommonPackage(file.parent.parent.parent)
    }

    val backButton by lazy {
        file.findChildFile(BackButtonFileManager.NAME)?.let { BackButtonFileManager(it) }
    }

    val colors by lazy {
        file.findChildFile(ColorsFileManager.NAME)?.let { ColorsFileManager(it) }
    }

    val uiText by lazy {
        file.findChildFile(UiTextFileManager.NAME)?.let { UiTextFileManager(it) }
    }

    private fun createAllChildren() {
        BackButtonFileManager.createNewInstance(this)
        ColorsFileManager.createNewInstance(this)
        UiTextFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("ui", CommonUiModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonUiPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                BackButtonFileManager.Companion,
                ColorsFileManager.Companion,
                UiTextFileManager.Companion
            )

        fun createNewInstance(insertionModule: CommonUiModule): CommonUiPackage? {
            val packageManager = insertionModule.createCodePackage()?.let { CommonUiPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
