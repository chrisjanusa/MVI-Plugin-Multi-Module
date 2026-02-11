package com.github.chrisjanusa.mvi.package_structure.manager.common.remote

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class CommonRemoteModuleGradleTemplate(
    module: CommonRemoteModule
) : Template(module, CommonRemoteModuleGradleManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage
            ?.appModule
            ?.moduleGradle
            ?.getRootPackage()
            ?.substringBeforeLast(".")
            ?: "// TODO Insert Package Name"

        return "plugins {\n" +
                "    id(\"$basePackage.android.library\")\n" +
                "    id(\"$basePackage.android.ktor\")\n" +
                "}\n" +
                "\n" +
                "android {\n" +
                "    namespace = \"$basePackage.common.remote\"\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "}"
    }
}
