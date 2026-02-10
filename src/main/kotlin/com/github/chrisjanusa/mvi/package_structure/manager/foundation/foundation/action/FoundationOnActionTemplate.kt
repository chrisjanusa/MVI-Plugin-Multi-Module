package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.Manager

internal class FoundationOnActionTemplate(
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String =
                "typealias OnAction = (Action) -> Unit"
}