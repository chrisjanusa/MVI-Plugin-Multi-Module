package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class AndroidKtorConventionPluginTemplate(
    packageManager: ConventionPluginPackage
) : Template(packageManager, AndroidKtorConventionPluginManager.NAME) {
    override fun createContent(): String {
        return "import org.gradle.api.Plugin\n" +
                "import org.gradle.api.Project\n" +
                "import org.gradle.api.artifacts.VersionCatalogsExtension\n" +
                "import org.gradle.kotlin.dsl.dependencies\n" +
                "import org.gradle.kotlin.dsl.getByType\n" +
                "\n" +
                "class AndroidKtorConventionPlugin : Plugin<Project> {\n" +
                "\n" +
                "    override fun apply(target: Project) {\n" +
                "        with(target) {\n" +
                "            val libs = extensions.getByType<VersionCatalogsExtension>().named(\"libs\")\n" +
                "\n" +
                "            dependencies {\n" +
                "                add(\"implementation\", project(\":common:model\"))\n" +
                "                add(\"implementation\", libs.findBundle(\"ktor\").get())\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
