package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.plugin.generated

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.manager.base.addIf
import com.github.chrisjanusa.mvi.package_structure.manager.old.FoundationSliceFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.FoundationStateFileManager

internal class PluginViewModelTemplate(
    private val hasState: Boolean,
    private val hasSlice: Boolean,
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String {
        val state = if (hasState) {
            pluginPackage?.state?.name ?: "plugin not found"
        } else FoundationStateFileManager.NO_STATE
        val slice = if (hasSlice) {
            pluginPackage?.slice?.name ?: "plugin not found"
        } else FoundationSliceFileManager.NO_SLICE
        return "import androidx.lifecycle.ViewModel\n" +
        "import ${foundationPackage?.action?.packagePath}\n" +
        "import ${foundationPackage?.action?.packagePathExcludingFile}.ActionEffect\n" +
        "import ${foundationPackage?.action?.packagePathExcludingFile}.StateEffect\n" +
        "import ${foundationPackage?.pluginViewModel?.packagePath}\n" +
        "import ${foundationPackage?.slice?.noSlicePackagePath}\n".addIf { !hasSlice } +
        "import ${foundationPackage?.state?.noStatePackagePath}\n".addIf { !hasState } +
        "import dev.zacsweers.metro.AppScope\n" +
        "import dev.zacsweers.metro.ContributesIntoMap\n" +
        "import dev.zacsweers.metro.Inject\n" +
        "import dev.zacsweers.metro.binding\n" +
        "import dev.zacsweers.metrox.viewmodel.ViewModelKey\n" +
        "\n" +
        "@Inject\n" +
        "@ViewModelKey($fileName::class)\n" +
        "@ContributesIntoMap(\n" +
        "    AppScope::class,\n" +
        "    binding<ViewModel>()\n" +
        ")\n" +
        "class $fileName(\n" +
        ") : PluginViewModel<${state}, ${slice}>() {\n" +
        "    override val initialState = ${state}()\n".addIf { hasState } +
        "    override val initialState = ${state}\n".addIf { !hasState } +
        "    override val initialSlice = ${slice}\n".addIf { !hasSlice } +
        "    override val initialSlice = ${slice}()\n".addIf { hasSlice } +
        "\n" +
        "\n" +
        "    override val stateEffectSet = setOf<StateEffect>(\n" +
        "    )\n" +
        "\n" +
        "    override val actionEffectSet = setOf<ActionEffect>(\n" +
        "    )\n" +
        "\n" +
        "    override fun reduce(action: ${foundationPackage?.action}, state: ${state}, slice: ${slice}) =\n" +
        "        when (action) {\n" +
        "            is ${pluginPackage?.pluginAction} -> action.reduce(state, slice)\n" +
        "            else -> state\n" +
        "        }\n" +
        "\n" +
        "    init {\n" +
        "        triggerEffects()\n" +
        "    }\n" +
        "}\n"
    }
}