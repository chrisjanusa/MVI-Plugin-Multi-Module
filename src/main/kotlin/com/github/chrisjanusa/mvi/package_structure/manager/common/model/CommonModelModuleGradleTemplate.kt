package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class CommonModelModuleGradleTemplate(
    module: CommonModelModule
) : Template(module, CommonModelModuleGradleManager.NAME) {
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
                "    namespace = \"$basePackage.common.service.model\"\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "    implementation(project(\":common:ui\"))\n" +
                "}"
    }
}
