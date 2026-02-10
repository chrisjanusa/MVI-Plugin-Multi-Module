package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationPluginTemplate(
    val foundationPluginPackage: FoundationPluginPackage,
    fileName: String
) : Template(
    packageManager = foundationPluginPackage,
    fileName = fileName
) {

    override fun createContent(): String {
        val foundationPackage = foundationPluginPackage.foundationPackage

        return  "import androidx.compose.runtime.Composable\n" +
                "import androidx.compose.ui.Modifier\n" +
                "import ${foundationPackage.action?.packagePath}\n" +
                "import ${foundationPackage.onAction?.packagePath}\n" +
                "import ${foundationPackage.pluginSlice?.packagePath}\n" +
                "import ${foundationPackage.pluginState?.packagePath}\n" +
                "\n" +
                "abstract class Plugin<State : PluginState, Slice : PluginSlice>: BasePlugin<State, Slice>() {\n" +
                "    @Composable\n" +
                "    fun PluginContent(\n" +
                "        slice: Slice,\n" +
                "        onChildAction: (Action) -> Unit,\n" +
                "    ) {\n" +
                "        PluginContentInternal(slice, onChildAction) { modifier, state, slice, onAction ->\n" +
                "            PluginContent(modifier, state, slice, onAction)\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    @Composable\n" +
                "    protected abstract fun PluginContent(modifier: Modifier, state: State, slice: Slice, onAction: OnAction)\n" +
                "}"
    }
}
