package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.plugin

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationBasePluginTemplate(
    val foundationPluginPackage: FoundationPluginPackage,
    fileName: String
) : Template(
    packageManager = foundationPluginPackage,
    fileName = fileName
) {

    override fun createContent(): String {
        val foundationPackage = foundationPluginPackage.foundationPackage
        return "import androidx.compose.foundation.layout.fillMaxSize\n" +
        "import androidx.compose.runtime.Composable\n" +
        "import androidx.compose.runtime.LaunchedEffect\n" +
        "import androidx.compose.runtime.getValue\n" +
        "import androidx.compose.ui.Modifier\n" +
        "import androidx.lifecycle.compose.collectAsStateWithLifecycle\n" +
        "import ${foundationPackage.action?.packagePath}\n" +
        "import ${foundationPackage.pluginState?.packagePath}\n" +
        "import ${foundationPackage.pluginSlice?.packagePath}\n" +
        "import ${foundationPackage.pluginViewModel?.packagePath}\n" +
        "\n" +
        "abstract class BasePlugin<State : PluginState, Slice : PluginSlice> {\n" +
        "    @Composable\n" +
        "    internal fun PluginContentInternal(\n" +
        "        slice: Slice,\n" +
        "        onChildAction: (Action) -> Unit,\n" +
        "        pluginContent: @Composable (\n" +
        "            modifier: Modifier,\n" +
        "            state: State,\n" +
        "            slice: Slice,\n" +
        "            onAction: (Action) -> Unit,\n" +
        "        ) -> Unit,\n" +
        "    ) {\n" +
        "        val viewModel: PluginViewModel<State, Slice> = pluginViewModel()\n" +
        "        LaunchedEffect(Unit) {\n" +
        "            viewModel.attachChildActionListener(onChildAction)\n" +
        "        }\n" +
        "        val state by viewModel.state.collectAsStateWithLifecycle()\n" +
        "        LaunchedEffect(slice) {\n" +
        "            viewModel.onSliceUpdate(slice)\n" +
        "        }\n" +
        "        pluginContent(\n" +
        "            Modifier.fillMaxSize(),\n" +
        "            state,\n" +
        "            slice\n" +
        "        ) { action ->\n" +
        "            viewModel.onAction(action)\n" +
        "        }\n" +
        "    }\n" +
        "\n" +
        "    @Composable\n" +
        "    abstract fun pluginViewModel(): PluginViewModel<State, Slice>\n" +
        "}"
    }
}
