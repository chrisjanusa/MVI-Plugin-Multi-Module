package com.github.chrisjanusa.mvi.package_structure.manager.build_logic

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class BuildLogicSettingsGradleTemplate(
    packageManager: BuildLogicPackage
) : Template(packageManager, BuildLogicSettingsGradleManager.NAME) {
    override fun createContent(): String {
        return "dependencyResolutionManagement {\n" +
                "    versionCatalogs {\n" +
                "        create(\"libs\") {\n" +
                "            from(files(\"../gradle/libs.versions.toml\")) // Create this file if not present.\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "rootProject.name = \"build-logic\""
    }
}
