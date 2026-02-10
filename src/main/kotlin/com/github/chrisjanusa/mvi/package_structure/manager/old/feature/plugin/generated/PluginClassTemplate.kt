package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.plugin.generated

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.manager.base.addIf
import com.github.chrisjanusa.mvi.package_structure.manager.old.FoundationSliceFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.FoundationStateFileManager

internal class PluginClassTemplate(
    private val hasState: Boolean,
    private val hasSlice: Boolean,
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String {
        val state = if (hasState) { pluginPackage?.state?.name ?: "plugin not found" } else FoundationStateFileManager.NO_STATE
        val slice = if (hasSlice) { pluginPackage?.slice?.name ?: "plugin not found" } else FoundationSliceFileManager.NO_SLICE
        val viewModel = pluginPackage?.viewModel?.name ?: "plugin not found"

        return "import androidx.compose.runtime.Composable\n" +
                "import androidx.compose.ui.Modifier\n" +
                "import ${pluginPackage?.uiPackage?.content?.packagePath}\n" +
                "import ${foundationPackage?.action?.packagePathExcludingFile}.OnAction\n" +
                "import ${foundationPackage?.plugin?.packagePath}\n" +
                "import ${foundationPackage?.slice?.packagePathExcludingFile}.NoSlice\n".addIf { !hasSlice } +
                "import ${foundationPackage?.state?.packagePathExcludingFile}.NoState\n".addIf { !hasState } +
                "import dev.zacsweers.metrox.viewmodel.metroViewModel\n" +
                "\n" +
                "object $fileName : Plugin<$state, $slice>() {\n" +
                "    @Composable\n" +
                "    override fun pluginViewModel(): $viewModel = metroViewModel()\n" +
                "\n" +
                "    @Composable\n" +
                "    override fun PluginContent(modifier: Modifier, state: $state, slice: $slice, onAction: OnAction) {\n" +
                "        ${pluginName}Content(\n" +
                "            modifier = modifier,\n" +
                "            state = state,\n".addIf { hasState } +
                "            slice = slice,\n".addIf { hasSlice } +
                "            onAction = onAction\n" +
                "        )\n" +
                "    }\n" +
                "}\n"
    }
}