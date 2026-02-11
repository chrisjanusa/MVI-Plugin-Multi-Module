package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class CommonUiModuleGradleTemplate(
    val module: CommonUiModule
) : Template(module, CommonUiModuleGradleManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage
            ?.appModule
            ?.moduleGradle
            ?.getRootPackage()
            ?.substringBeforeLast(".")
            ?: "// TODO Insert Package Name"

        return "plugins {\n" +
                "    id(\"$basePackage.android.library\")\n" +
                "    id(\"$basePackage.android.compose\")\n" +
                "}\n" +
                "\n" +
                "android {\n" +
                "    namespace = \"$basePackage.common.ui\"\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "}"
    }
}
