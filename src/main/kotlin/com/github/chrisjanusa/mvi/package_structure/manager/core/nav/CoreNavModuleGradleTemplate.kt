package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

internal class CoreNavModuleGradleTemplate(
    coreNavModule: CoreNavModule,
    fileName: String,
) : Template(
    packageManager = coreNavModule,
    fileName = fileName
) {

    override fun createContent(): String {
        val basePackage = projectPackage
            ?.appModule
            ?.moduleGradle
            ?.getRootPackage()
            ?.substringBeforeLast(".")
            ?: "// TODO Insert Package Name"

        return "plugins {\n" +
        "    id(\"$basePackage.android.library\")\n" +
        "}\n" +
        "\n" +
        "android {\n" +
        "    namespace = \"$basePackage.core.nav\"\n" +
        "}\n" +
        "\n" +
        "dependencies {\n" +
        "    implementation(libs.navigation)\n" +
        "\n" +
        "    // Modules\n" +
        "    implementation(project(\":foundation\"))\n" +
        "}"
    }
}