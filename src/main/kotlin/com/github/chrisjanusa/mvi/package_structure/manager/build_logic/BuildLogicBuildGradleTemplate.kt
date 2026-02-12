package com.github.chrisjanusa.mvi.package_structure.manager.build_logic

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class BuildLogicBuildGradleTemplate(
    packageManager: BuildLogicPackage
) : Template(packageManager, BuildLogicBuildGradleManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage?.basePackagePath ?: "TODO: Add Base Package"

        return "import org.gradle.kotlin.dsl.creating\n" +
                "import org.gradle.kotlin.dsl.getValue\n" +
                "\n" +
                "plugins {\n" +
                "    `kotlin-dsl`\n" +
                "}\n" +
                "\n" +
                "gradlePlugin {\n" +
                "    plugins {\n" +
                "        val androidLibrary by creating {\n" +
                "            id = \"$basePackage.android.library\"\n" +
                "            implementationClass = \"AndroidLibraryConventionPlugin\"\n" +
                "        }\n" +
                "        val androidCompose by creating {\n" +
                "            id = \"$basePackage.android.compose\"\n" +
                "            implementationClass = \"AndroidComposeConventionPlugin\"\n" +
                "        }\n" +
                "        val androidFeature by creating {\n" +
                "            id = \"$basePackage.android.feature\"\n" +
                "            implementationClass = \"AndroidFeatureConventionPlugin\"\n" +
                "        }\n" +
                "        val androidMetro by creating {\n" +
                "            id = \"$basePackage.android.metro\"\n" +
                "            implementationClass = \"AndroidMetroConventionPlugin\"\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "repositories {\n" +
                "    google()\n" +
                "    mavenCentral()\n" +
                "    gradlePluginPortal()\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "    compileOnly(libs.gradle)\n" +
                "    compileOnly(libs.kotlin.gradle.plugin)\n" +
                "    compileOnly(libs.kotlin.compose.compiler.plugin)\n" +
                "    implementation(libs.metro.gradle.plugin)\n" +
                "}"
    }
}
