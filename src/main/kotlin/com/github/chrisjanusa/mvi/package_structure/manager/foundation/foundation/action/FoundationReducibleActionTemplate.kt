package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.action

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.Manager

internal class FoundationReducibleActionTemplate(
    val foundationActionPackage: FoundationActionPackage,
    fileName: String
) : Template(
    packageManager = foundationActionPackage,
    fileName = fileName
) {

    override fun createContent(): String {
        val foundationStatePackage = foundationActionPackage.foundationPackage.statePackage
        return "import ${foundationStatePackage?.pluginSlice?.packagePath}\n" +
        "import ${foundationStatePackage?.pluginState?.packagePath}\n" +
        "\n" +
        "interface ReducibleAction<ActionState: State, ActionSlice: Slice>: Action {\n" +
        "    fun reduce(state: ActionState, slice: ActionSlice) : ActionState = state\n" +
        "}"
    }
}