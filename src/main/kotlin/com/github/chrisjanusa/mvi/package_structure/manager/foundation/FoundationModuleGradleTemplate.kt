package com.github.chrisjanusa.mvi.package_structure.manager.foundation

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

internal class FoundationModuleGradleTemplate(
    val foundationModule: FoundationModule,
    fileName: String,
) : Template(
    packageManager = foundationModule,
    fileName = fileName
) {

    override fun createContent(): String {
        val basePackage = foundationModule
            .projectPackage
            .appModule
            ?.moduleGradle
            ?.getRootPackage()
            ?.substringBeforeLast(".")
            ?: "// TODO Insert Package Name"
        return "plugins {\n" +
        "    id(\"$basePackage.android.compose\")\n" +
        "}\n" +
        "\n" +
        "android {\n" +
        "    namespace = \"$basePackage.foundation\"\n" +
        "}\n" +
        "\n" +
        "dependencies {\n" +
        "    implementation(libs.androidx.lifecycle.viewmodel.ktx)\n" +
        "}"
    }
}