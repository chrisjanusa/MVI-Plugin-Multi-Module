package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.intellij.ide.plugins.ActionDescriptorName

internal class AppViewModelTemplate(
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String =
        "import androidx.lifecycle.ViewModel\n" +
        "import androidx.lifecycle.viewModelScope\n" +
        "import ${projectPackage?.foundationModule?.foundationPackage?.appAction?.packagePath}\n" +
        "import ${projectPackage?.foundationModule?.foundationPackage?.navAction?.packagePath}\n" +
        "import ${projectPackage?.foundationModule?.foundationPackage?.appEffect?.packagePath}\n" +
        "import dev.zacsweers.metro.AppScope\n" +
        "import dev.zacsweers.metro.ContributesIntoMap\n" +
        "import dev.zacsweers.metro.Inject\n" +
        "import dev.zacsweers.metrox.viewmodel.ViewModelKey\n" +
        "import kotlinx.coroutines.Dispatchers\n" +
        "import kotlinx.coroutines.flow.MutableSharedFlow\n" +
        "import kotlinx.coroutines.flow.asSharedFlow\n" +
        "import kotlinx.coroutines.flow.filter\n" +
        "import kotlinx.coroutines.launch\n" +
        "\n" +
        "@Inject\n" +
        "@ViewModelKey(AppViewModel::class)\n" +
        "@ContributesIntoMap(AppScope::class)\n" +
        "class AppViewModel(private val effectSet: Set<AppEffect>) : ViewModel() {\n" +
        "\n" +
        "    private val actionFlow = MutableSharedFlow<AppAction>()\n" +
        "    val navActionFlow = actionFlow.asSharedFlow().filter { it is NavAction }\n" +
        "\n" +
        "    fun onAction(action: AppAction) {\n" +
        "        viewModelScope.launch {\n" +
        "            actionFlow.emit(action)\n" +
        "        }\n" +
        "    }\n" +
        "\n" +
        "    init {\n" +
        "        viewModelScope.launch(Dispatchers.Default) {\n" +
        "            effectSet.forEach { effect ->\n" +
        "                launch {\n" +
        "                    effect.launchEffect(\n" +
        "                        actionFlow = actionFlow,\n" +
        "                        onAction = {\n" +
        "                            onAction(it)\n" +
        "                        }\n" +
        "                    )\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    }\n" +
        "}"
}