package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationAppEffectFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationEffectPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("AppEffect", FoundationEffectPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationAppEffectFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationEffectPackage): FoundationAppEffectFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationAppEffectTemplate(
                    insertionPackage,
                    NAME
                ).createContent()
            )?.let { FoundationAppEffectFileManager(it) }
        }
    }
}
