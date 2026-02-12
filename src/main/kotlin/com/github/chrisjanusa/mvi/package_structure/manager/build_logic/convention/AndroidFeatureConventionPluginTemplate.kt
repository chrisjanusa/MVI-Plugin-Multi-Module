package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class AndroidFeatureConventionPluginTemplate(
    packageManager: ConventionPluginPackage
) : Template(packageManager, AndroidFeatureConventionPluginManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage?.basePackagePath ?: "com.example"

        return "import org.gradle.api.Plugin\n" +
                "import org.gradle.api.Project\n" +
                "import org.gradle.api.artifacts.VersionCatalogsExtension\n" +
                "import org.gradle.kotlin.dsl.dependencies\n" +
                "import org.gradle.kotlin.dsl.getByType\n" +
                "\n" +
                "class AndroidFeatureConventionPlugin : Plugin<Project> {\n" +
                "    override fun apply(target: Project) {\n" +
                "        with(target) {\n" +
                "            pluginManager.apply(\"$basePackage.android.compose\")\n" +
                "            pluginManager.apply(\"$basePackage.android.metro\")\n" +
                "            pluginManager.apply(\"org.jetbrains.kotlin.plugin.serialization\")\n" +
                "\n" +
                "            val libs = extensions.getByType<VersionCatalogsExtension>().named(\"libs\")\n" +
                "\n" +
                "            dependencies {\n" +
                "                add(\"implementation\", project(\":foundation\"))\n" +
                "                add(\"implementation\", project(\":common:ui\"))\n" +
                "                add(\"implementation\", project(\":common:model\"))\n" +
                "                add(\"implementation\", project(\":core:nav\"))\n" +
                "                // add(\"implementation\", project(\":feature:book:domain\")) // TODO: Add domain module if applicable\n" +
                "\n" +
                "                add(\"implementation\", libs.findLibrary(\"androidx.lifecycle.runtime.compose\").get())\n" +
                "                add(\"implementation\", libs.findLibrary(\"androidx.lifecycle.viewmodel.ktx\").get())\n" +
                "                add(\"implementation\", libs.findLibrary(\"navigation\").get())\n" +
                "                add(\"implementation\", libs.findLibrary(\"kotlinx.serialization.json\").get())\n" +
                "                add(\"implementation\", libs.findBundle(\"coil\").get())\n" +
                "\n" +
                "                add(\"testImplementation\", libs.findBundle(\"testing.unit\").get())\n" +
                "                add(\"androidTestImplementation\", libs.findBundle(\"testing.android\").get())\n" +
                "                add(\"androidTestImplementation\", project(\":common:testing\"))\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
