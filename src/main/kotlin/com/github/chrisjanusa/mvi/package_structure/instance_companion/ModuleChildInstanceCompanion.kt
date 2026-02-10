package com.github.chrisjanusa.mvi.package_structure.instance_companion

import com.github.chrisjanusa.mvi.package_structure.getManagerOfType
import com.intellij.openapi.vfs.VirtualFile

 abstract class ModuleChildInstanceCompanion(private val parentModuleCompanion: InstanceCompanion): InstanceCompanion() {
    override fun isInstance(file: VirtualFile?): Boolean {
        return file?.getManagerOfType(parentModuleCompanion) != null
    }
}