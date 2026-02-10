package com.github.chrisjanusa.mvi.package_structure.manager.app.root.nav

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

internal class NavEffectFileTemplate(
    packageManager: Manager,
    fileName: String,
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {
    override fun createContent(): String =
        "import ${projectPackage?.foundationModule?.foundationPackage?.appAction?.packagePath}\n" +
        "import ${projectPackage?.foundationModule?.foundationPackage?.onAppAction?.packagePath}\n" +
        "import ${projectPackage?.foundationModule?.foundationPackage?.appEffect?.packagePath}\n" +
        // TODO: core/nav package CoreNavAction "import ${}\n" +
        "import dev.zacsweers.metro.AppScope\n" +
        "import dev.zacsweers.metro.ContributesIntoSet\n" +
        "import kotlinx.coroutines.flow.Flow\n" +
        "import kotlinx.coroutines.flow.collectLatest\n" +
        "import kotlinx.coroutines.flow.filterIsInstance\n" +
        "\n" +
        "@ContributesIntoSet(AppScope::class)\n" +
        "class OnNavToBookDetailsEffect : AppEffect {\n" +
        "    override suspend fun launchEffect(actionFlow: Flow<AppAction>, onAction: OnAppAction) {\n" +
        "        actionFlow.filterIsInstance</* TODO: Add trigger action */>().collectLatest {\n" +
        "            onAction(CoreNavAction.OnNavigateToScreen(/* TODO: Add destination Component ID */))\n" +
        "        }\n" +
        "    }\n" +
        "}"
}