package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class FoundationStateEffectFileManager(file: VirtualFile) : FileManager(file) {
    val foundationPackage by lazy {
        FoundationEffectPackage(file.parent).foundationPackage
    }

    companion object : StaticChildInstanceCompanion("StateEffect", FoundationEffectPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = FoundationStateEffectFileManager(virtualFile)

        fun createNewInstance(insertionPackage: FoundationEffectPackage): FoundationStateEffectFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                FoundationStateEffectTemplate(insertionPackage, NAME).createContent()
            )?.let { FoundationStateEffectFileManager(it) }
        }
    }
}
