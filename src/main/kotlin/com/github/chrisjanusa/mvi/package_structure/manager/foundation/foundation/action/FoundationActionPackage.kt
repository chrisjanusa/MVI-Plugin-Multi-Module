package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.intellij.openapi.vfs.VirtualFile

class FoundationActionPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val foundationPackage by lazy {
        FoundationPackage(file.parent)
    }

    val action by lazy {
        file.findChildFile(FoundationActionFileManager.NAME)?.let { FoundationActionFileManager(it) }
    }

    val appAction by lazy {
        file.findChildFile(FoundationAppActionFileManager.NAME)?.let { FoundationAppActionFileManager(it) }
    }

    val navAction by lazy {
        file.findChildFile(FoundationNavActionFileManager.NAME)?.let { FoundationNavActionFileManager(it) }
    }

    val onAction by lazy {
        file.findChildFile(FoundationOnActionFileManager.NAME)?.let { FoundationOnActionFileManager(it) }
    }

    val onAppAction by lazy {
        file.findChildFile(FoundationOnAppActionFileManager.NAME)?.let { FoundationOnAppActionFileManager(it) }
    }

    val reducibleAction by lazy {
        file.findChildFile(FoundationReducibleActionFileManager.NAME)?.let { FoundationReducibleActionFileManager(it) }
    }

    private fun createAllChildren() {
        FoundationActionFileManager.createNewInstance(this)
        FoundationAppActionFileManager.createNewInstance(this)
        FoundationNavActionFileManager.createNewInstance(this)
        FoundationOnActionFileManager.createNewInstance(this)
        FoundationOnAppActionFileManager.createNewInstance(this)
        FoundationReducibleActionFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("action", FoundationPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationActionPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                FoundationActionFileManager.Companion,
                FoundationAppActionFileManager.Companion,
                FoundationNavActionFileManager.Companion,
                FoundationOnActionFileManager.Companion,
                FoundationOnAppActionFileManager.Companion,
                FoundationReducibleActionFileManager.Companion,
            )

        fun createNewInstance(insertionModule: FoundationPackage): FoundationActionPackage? {
            val packageManager = insertionModule.createNewDirectory(NAME)?.let { FoundationActionPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}