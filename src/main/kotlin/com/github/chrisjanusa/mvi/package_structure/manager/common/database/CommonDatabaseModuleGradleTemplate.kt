package com.github.chrisjanusa.mvi.package_structure.manager.common.database

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class CommonDatabaseModuleGradleTemplate(
    module: CommonDatabaseModule
) : Template(module, CommonDatabaseModuleGradleManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage
            ?.appModule
            ?.moduleGradle
            ?.getRootPackage()
            ?.substringBeforeLast(".")
            ?: "// TODO Insert Package Name"

        return "plugins {\n" +
                "    id(\"$basePackage.android.library\")\n" +
                "    id(\"$basePackage.android.room\")\n" +
                "}\n" +
                "\n" +
                "android {\n" +
                "    namespace = \"$basePackage.common.database\"\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "}"
    }
}
