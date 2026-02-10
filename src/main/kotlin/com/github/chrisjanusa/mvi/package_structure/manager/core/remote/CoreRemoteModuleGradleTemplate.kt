package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.manager.core.nav.CoreNavModule

internal class CoreRemoteModuleGradleTemplate(
    val coreRemoteModule: CoreRemoteModule,
    fileName: String,
) : Template(
    packageManager = coreRemoteModule,
    fileName = fileName
) {

    override fun createContent(): String {
        val basePackage = coreRemoteModule
            .projectPackage
            .appModule
            ?.moduleGradle
            ?.getRootPackage()
            ?.substringBeforeLast(".")
            ?: "// TODO Insert Package Name"

        return "plugins {\n" +
        "    id(\"$basePackage.android.library\")\n" +
        "    id(\"$basePackage.android.metro\")\n" +
        "}\n" +
        "\n" +
        "android {\n" +
        "    namespace = \"$basePackage.core.remote\"\n" +
        "}\n" +
        "\n" +
        "dependencies {\n" +
        "    implementation(libs.bundles.ktor)\n" +
        "}"
    }
}