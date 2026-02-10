package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationNoSlicePluginTemplate(
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
                "import ${foundationPackage.noSlice?.packagePath}\n" +
                "import ${foundationPackage.pluginState?.packagePath}\n" +
                "\n" +
                "abstract class NoSlicePlugin<State : PluginState>: BasePlugin<State, NoSlice>() {\n" +
                "    @Composable\n" +
                "    fun PluginContent(\n" +
                "        onChildAction: (Action) -> Unit,\n" +
                "    ) {\n" +
                "        PluginContentInternal(NoSlice, onChildAction) { modifier, state, _, onAction ->\n" +
                "            PluginContent(modifier, state, onAction)\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    @Composable\n" +
                "    protected abstract fun PluginContent(modifier: Modifier, state: State, onAction: OnAction)\n" +
                "}"
    }
}
