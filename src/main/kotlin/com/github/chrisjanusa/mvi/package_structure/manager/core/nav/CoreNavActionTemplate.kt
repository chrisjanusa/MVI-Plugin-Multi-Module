package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class CoreNavActionTemplate(
    packageManager: Manager
) : Template(packageManager, CoreNavActionFileManager.NAME) {
    override fun createContent(): String {
        val foundationPackage = projectPackage?.foundationModule?.foundationPackage
        return "import ${foundationPackage?.navAction?.packagePath}\n" +
                "\n" +
                "sealed interface $fileName: NavAction {\n" +
                "    data object OnBackClick : CoreNavAction\n" +
                "    data class OnNavigateToScreen(val navComponent: NavComponentId) : CoreNavAction\n" +
                "}"
    }
}
