package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

internal class FoundationActionEffectTemplate(
    val effectPackage: FoundationEffectPackage,
    fileName: String
) : Template(
    packageManager = effectPackage,
    fileName = fileName
) {
    override fun createContent(): String =
        "import ${effectPackage.foundationPackage.actionPackage?.action?.packagePath}\n" +
        "import ${effectPackage.foundationPackage.actionPackage?.onAction?.packagePath}\n" +
        "import kotlinx.coroutines.flow.Flow\n" +
        "\n" +
        "interface ActionEffect {\n" +
        "    suspend fun launchEffect(actionFlow: Flow<Action>, onAction: OnAction)\n" +
        "}"
}
