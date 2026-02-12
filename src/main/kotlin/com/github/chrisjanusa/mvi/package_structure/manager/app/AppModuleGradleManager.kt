package com.github.chrisjanusa.mvi.package_structure.manager.app

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.FoundationModule
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class AppModuleGradleManager(file: VirtualFile) : ModuleGradleManager(file) {

    companion object : StaticChildInstanceCompanion("build.gradle", FoundationModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = AppModuleGradleManager(virtualFile)
    }
}