package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation

import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.FoundationModule
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action.FoundationActionPackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect.FoundationEffectPackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin.FoundationPluginPackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state.FoundationStatePackage
import com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.viewmodel.FoundationViewModelPackage
import com.intellij.openapi.vfs.VirtualFile

class FoundationPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val actionPackage by lazy {
        file.findChild(FoundationActionPackage.NAME)?.let { FoundationActionPackage(it) }
    }
    val action by lazy { actionPackage?.action }
    val appAction by lazy { actionPackage?.appAction }
    val navAction by lazy { actionPackage?.navAction }
    val onAction by lazy { actionPackage?.onAction }
    val onAppAction by lazy { actionPackage?.onAppAction }
    val reducibleAction by lazy { actionPackage?.reducibleAction }

    val statePackage by lazy {
        file.findChild(FoundationStatePackage.NAME)?.let { FoundationStatePackage(it) }
    }
    val noSlice by lazy { statePackage?.noSlice }
    val noState by lazy { statePackage?.noState }
    val pluginSlice by lazy { statePackage?.pluginSlice }
    val pluginState by lazy { statePackage?.pluginState }

    val effectPackage by lazy {
        file.findChild(FoundationEffectPackage.NAME)?.let { FoundationEffectPackage(it) }
    }
    val stateEffect by lazy { effectPackage?.stateEffect }
    val appEffect by lazy { effectPackage?.appEffect }
    val actionEffect by lazy { effectPackage?.actionEffect }

    val pluginPackage by lazy {
        file.findChild(FoundationPluginPackage.NAME)?.let { FoundationPluginPackage(it) }
    }
    val plugin by lazy { pluginPackage?.plugin }
    val noSlicePlugin by lazy { pluginPackage?.noSlicePlugin }

    val viewModelPackage by lazy {
        file.findChild(FoundationViewModelPackage.NAME)?.let { FoundationViewModelPackage(it) }
    }
    val noSlicePluginViewModel by lazy { viewModelPackage?.noSlicePluginViewModel }
    val pluginViewModel by lazy { viewModelPackage?.pluginViewModel }

    private fun createAllChildren() {
        FoundationActionPackage.createNewInstance(this)
        FoundationStatePackage.createNewInstance(this)
        FoundationEffectPackage.createNewInstance(this)
        FoundationPluginPackage.createNewInstance(this)
        FoundationViewModelPackage.createNewInstance(this)
    }

    companion object : StaticInstanceCompanion("foundation") {
        override fun createInstance(virtualFile: VirtualFile) = FoundationPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                FoundationActionPackage.Companion,
                FoundationStatePackage.Companion,
                FoundationEffectPackage.Companion,
                FoundationPluginPackage.Companion,
                FoundationViewModelPackage.Companion,
            )

        fun createNewInstance(insertionModule: FoundationModule): FoundationPackage? {
            val packageManager = insertionModule.createCodePackage()?.let { FoundationPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}