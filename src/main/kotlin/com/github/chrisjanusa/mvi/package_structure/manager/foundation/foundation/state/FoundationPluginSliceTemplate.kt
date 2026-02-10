package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.state

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.Manager

internal class FoundationPluginSliceTemplate(
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String =
                "interface PluginSlice"
}