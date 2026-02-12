package com.github.chrisjanusa.mvi.package_structure.manager.build_logic.convention

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class AndroidLibraryConventionPluginTemplate(
    packageManager: ConventionPluginPackage
) : Template(packageManager, AndroidLibraryConventionPluginManager.NAME) {
    override fun createContent(): String {
        return "import com.android.build.api.dsl.LibraryExtension\n" +
                "import org.gradle.api.JavaVersion\n" +
                "import org.gradle.api.Plugin\n" +
                "import org.gradle.api.Project\n" +
                "import org.gradle.kotlin.dsl.configure\n" +
                "\n" +
                "class AndroidLibraryConventionPlugin : Plugin<Project> {\n" +
                "    override fun apply(target: Project) {\n" +
                "        with(target) {\n" +
                "            pluginManager.apply(\"com.android.library\")\n" +
                "\n" +
                "            extensions.configure<LibraryExtension> {\n" +
                "                compileSdk = 36\n" +
                "\n" +
                "                defaultConfig {\n" +
                "                    minSdk = 28\n" +
                "                }\n" +
                "\n" +
                "                compileOptions {\n" +
                "                    sourceCompatibility = JavaVersion.VERSION_17\n" +
                "                    targetCompatibility = JavaVersion.VERSION_17\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
