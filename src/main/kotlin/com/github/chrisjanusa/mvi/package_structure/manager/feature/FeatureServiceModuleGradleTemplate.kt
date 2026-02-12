package com.github.chrisjanusa.mvi.package_structure.manager.feature

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FeatureServiceModuleGradleTemplate(
    val module: FeatureServiceModule
) : Template(module, FeatureServiceModuleGradleManager.NAME) {
    override fun createContent(): String {
        val basePackage = projectPackage?.basePackagePath ?: "com.example"
        val featureName = module.featureName
        val namespace = "$basePackage.feature.$featureName.service"

        return "plugins {\n" +
                "    id(\"$basePackage.android.library\")\n" +
                "}\n" +
                "\n" +
                "android {\n" +
                "    namespace = \"$namespace\"\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "    implementation(project(\":feature:$featureName:domain\"))\n" +
                "}"
    }
}
