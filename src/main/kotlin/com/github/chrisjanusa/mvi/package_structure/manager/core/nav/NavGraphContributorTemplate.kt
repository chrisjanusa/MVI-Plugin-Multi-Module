package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class NavGraphContributorTemplate(
    packageManager: Manager
) : Template(packageManager, NavGraphContributorFileManager.NAME) {
    override fun createContent(): String {
        val foundationPackage = projectPackage?.foundationModule?.foundationPackage
        return  "import androidx.navigation.NavGraphBuilder\n" +
                "import ${foundationPackage?.onAppAction?.packagePath}\n" +
                "\n" +
                "interface $fileName {\n" +
                "    fun register(builder: NavGraphBuilder, onAppAction: OnAppAction)\n" +
                "}"
    }
}
