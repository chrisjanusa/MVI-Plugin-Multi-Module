package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class AndroidComposeConventionPluginTemplate(
    packageManager: ConventionPluginPackage
) : Template(packageManager, AndroidComposeConventionPluginManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage?.basePackagePath ?: "com.example"

        return "import com.android.build.api.dsl.LibraryExtension\n" +
                "import org.gradle.api.Plugin\n" +
                "import org.gradle.api.Project\n" +
                "import org.gradle.api.artifacts.VersionCatalogsExtension\n" +
                "import org.gradle.kotlin.dsl.configure\n" +
                "import org.gradle.kotlin.dsl.dependencies\n" +
                "import org.gradle.kotlin.dsl.getByType\n" +
                "\n" +
                "class AndroidComposeConventionPlugin : Plugin<Project> {\n" +
                "    override fun apply(target: Project) {\n" +
                "        with(target) {\n" +
                "            pluginManager.apply(\"$basePackage.android.library\")\n" +
                "            pluginManager.apply(\"org.jetbrains.kotlin.plugin.compose\")\n" +
                "\n" +
                "            val libs = extensions.getByType<VersionCatalogsExtension>().named(\"libs\")\n" +
                "\n" +
                "            extensions.configure<LibraryExtension> {\n" +
                "                buildFeatures {\n" +
                "                    compose = true\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            dependencies {\n" +
                "                val bom = libs.findLibrary(\"androidx.compose.bom\").get()\n" +
                "                add(\"implementation\", platform(bom))\n" +
                "                add(\"androidTestImplementation\", platform(bom))\n" +
                "                \n" +
                "                add(\"implementation\", libs.findBundle(\"compose.ui\").get())\n" +
                "                add(\"androidTestImplementation\", libs.findBundle(\"compose.test\").get())\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
