package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.effect

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationAppEffectTemplate(
    val foundationEffectPackage: FoundationEffectPackage,
    fileName: String
) : Template(
    packageManager = foundationEffectPackage,
    fileName = fileName
) {

    override fun createContent(): String {
        val foundationPackage = foundationEffectPackage.foundationPackage
        return "import ${foundationPackage.appAction?.packagePath}\n" +
        "import ${foundationPackage.onAppAction?.packagePath}\n" +
        "import kotlinx.coroutines.flow.Flow\n" +
        "\n" +
        "interface AppEffect {\n" +
        "    suspend fun launchEffect(actionFlow: Flow<AppAction>, onAction: OnAppAction)\n" +
        "}"
    }
}
