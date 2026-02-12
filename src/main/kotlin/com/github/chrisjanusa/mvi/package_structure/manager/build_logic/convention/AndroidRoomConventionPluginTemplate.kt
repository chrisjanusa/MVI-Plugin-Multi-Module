package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class AndroidRoomConventionPluginTemplate(
    packageManager: ConventionPluginPackage
) : Template(packageManager, AndroidRoomConventionPluginManager.NAME) {
    override fun createContent(): String {
        return "import org.gradle.api.Plugin\n" +
                "import org.gradle.api.Project\n" +
                "import org.gradle.api.artifacts.VersionCatalogsExtension\n" +
                "import org.gradle.kotlin.dsl.dependencies\n" +
                "import org.gradle.kotlin.dsl.getByType\n" +
                "\n" +
                "class AndroidRoomConventionPlugin : Plugin<Project> {\n" +
                "\n" +
                "    override fun apply(target: Project) {\n" +
                "        with(target) {\n" +
                "            pluginManager.apply(\"com.google.devtools.ksp\")\n" +
                "            pluginManager.apply(\"org.jetbrains.kotlin.plugin.serialization\")\n" +
                "\n" +
                "            val libs = extensions.getByType<VersionCatalogsExtension>().named(\"libs\")\n" +
                "\n" +
                "            dependencies {\n" +
                "                add(\"implementation\", libs.findLibrary(\"kotlinx.serialization.json\").get())\n" +
                "                add(\"implementation\", libs.findBundle(\"room\").get())\n" +
                "                add(\"ksp\", libs.findLibrary(\"androidx.room.compiler\").get())\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
