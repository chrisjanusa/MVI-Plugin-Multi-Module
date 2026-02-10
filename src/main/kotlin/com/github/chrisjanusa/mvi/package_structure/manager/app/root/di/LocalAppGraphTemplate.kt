package com.github.chrisjanusa.mvi.package_structure.manager.app.root.di

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.Manager

internal class LocalAppGraphTemplate(
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String =
                "import androidx.compose.runtime.staticCompositionLocalOf\n" +
                "\n" +
                "val LocalAppGraph = staticCompositionLocalOf<MetroAppGraph> {\n" +
                "    error(\"MetroAppGraph not provided! Ensure you wrapped your UI in CompositionLocalProvider.\") \n" +
                "}"
}