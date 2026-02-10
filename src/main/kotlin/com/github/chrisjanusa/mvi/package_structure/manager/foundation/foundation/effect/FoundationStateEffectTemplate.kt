package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationStateEffectTemplate(
    val foundationEffectPackage: FoundationEffectPackage,
    fileName: String
) : Template(
    packageManager = foundationEffectPackage,
    fileName = fileName
) {
    override fun createContent(): String {
        val foundationPackage = foundationEffectPackage.foundationPackage
        return "import ${foundationPackage.action?.packagePath}.action.Action\n" +
                "import ${foundationPackage.onAction?.packagePath}.action.OnAction\n" +
                "import ${foundationPackage.pluginSlice?.packagePath}.state.PluginSlice\n" +
                "import ${foundationPackage.pluginState?.packagePath}.state.PluginState\n" +
                "import kotlinx.coroutines.flow.Flow\n" +
                "import kotlinx.coroutines.flow.StateFlow\n" +
                "\n" +
                "interface StateEffect<EffectState : PluginState, EffectSlice : PluginSlice> {\n" +
                "    suspend fun launchEffect(actionFlow: Flow<Action>, stateFlow: StateFlow<EffectState>, sliceFlow: StateFlow<EffectSlice>, onAction: OnAction)\n" +
                "}"
    }
}
