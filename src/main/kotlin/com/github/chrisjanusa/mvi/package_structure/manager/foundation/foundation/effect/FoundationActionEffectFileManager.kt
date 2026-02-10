package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationActionEffectFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationEffectPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("ActionEffect", FoundationEffectPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationActionEffectFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationEffectPackage): FoundationActionEffectFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationActionEffectTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationActionEffectFileManager(it) }
        }
    }
}
