package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.viewmodel

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationNoSlicePluginViewModelTemplate(
    val foundationViewModelPackage: FoundationViewModelPackage,
    fileName: String
) : Template(
    packageManager = foundationViewModelPackage,
    fileName = fileName
) {

    override fun createContent(): String {
        val foundationPackage = foundationViewModelPackage.foundationPackage
        return "import ${foundationPackage.noSlice?.packagePath}\n" +
                "import ${foundationPackage.pluginState?.packagePath}\n" +
                "\n" +
                "abstract class NoSlicePluginViewModel<State : PluginState>() : PluginViewModel<State, NoSlice>() {\n" +
                "    override val initialSlice = NoSlice\n" +
                "}"
    }
}
