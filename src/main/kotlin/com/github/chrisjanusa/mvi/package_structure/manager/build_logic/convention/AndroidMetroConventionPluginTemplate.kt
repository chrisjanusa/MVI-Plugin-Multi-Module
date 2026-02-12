package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class AndroidMetroConventionPluginTemplate(
    packageManager: ConventionPluginPackage
) : Template(packageManager, AndroidMetroConventionPluginManager.NAME) {
    override fun createContent(): String {
        return "import org.gradle.api.Plugin\n" +
                "import org.gradle.api.Project\n" +
                "import org.gradle.api.artifacts.VersionCatalogsExtension\n" +
                "import org.gradle.kotlin.dsl.dependencies\n" +
                "import org.gradle.kotlin.dsl.getByType\n" +
                "\n" +
                "class AndroidMetroConventionPlugin : Plugin<Project> {\n" +
                "    override fun apply(target: Project) {\n" +
                "        with(target) {\n" +
                "            pluginManager.apply(\"dev.zacsweers.metro\")\n" +
                "            pluginManager.apply(\"com.google.devtools.ksp\")\n" +
                "\n" +
                "            val libs = extensions.getByType<VersionCatalogsExtension>().named(\"libs\")\n" +
                "\n" +
                "            dependencies {\n" +
                "                add(\"implementation\", libs.findBundle(\"metro\").get())\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
