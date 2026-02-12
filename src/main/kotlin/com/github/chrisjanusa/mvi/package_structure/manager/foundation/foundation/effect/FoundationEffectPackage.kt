package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.FoundationPackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action.FoundationActionFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action.FoundationAppActionFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action.FoundationNavActionFileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationEffectPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val foundationPackage by lazy {
        FoundationPackage(file.parent)
    }

    val actionEffect by lazy {
        file.findChildFile(FoundationActionEffectFileManager.NAME)?.let { FoundationActionEffectFileManager(it) }
    }

    val stateEffect by lazy {
        file.findChildFile(FoundationStateEffectFileManager.NAME)?.let { FoundationStateEffectFileManager(it) }
    }

    val appEffect by lazy {
        file.findChildFile(FoundationAppEffectFileManager.NAME)?.let { FoundationAppEffectFileManager(it) }
    }

    private fun createAllChildren() {
        FoundationActionEffectFileManager.createNewInstance(this)
        FoundationStateEffectFileManager.createNewInstance(this)
        FoundationAppEffectFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("effect", FoundationPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationEffectPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                FoundationActionEffectFileManager.Companion,
                FoundationStateEffectFileManager.Companion,
                FoundationAppEffectFileManager.Companion
            )

        fun createNewInstance(insertionModule: FoundationPackage): FoundationEffectPackage? {
            val packageManager = insertionModule.createNewDirectory(NAME)?.let { FoundationEffectPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
