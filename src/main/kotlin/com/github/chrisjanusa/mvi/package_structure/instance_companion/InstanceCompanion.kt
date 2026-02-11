package com.github.chrisjanusa.mvi.package_structure.instance_companion


import com.github.chrisjanusa.mvi.package_structure.Manager
import com.intellij.openapi.vfs.VirtualFile

abstract class InstanceCompanion {
    open val allChildrenInstanceCompanions: List<InstanceCompanion>
        get() = emptyList()

    abstract fun isInstance(file: VirtualFile?): Boolean

    abstract fun createInstance(virtualFile: VirtualFile): Manager?
}