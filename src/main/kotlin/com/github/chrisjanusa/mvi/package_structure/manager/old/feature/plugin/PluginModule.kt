package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.plugin

import com.github.chrisjanusa.mvi.package_structure.manager.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.intellij.openapi.vfs.VirtualFile

class PluginModule(file: VirtualFile) : PackageManager(file) {

    companion object : ModuleInstanceCompanion() {
        override fun isInstance(file: VirtualFile?): Boolean = false 

        override fun createInstance(virtualFile: VirtualFile): Manager? = null 

        override fun getBuildGradleTemplate(namespace: String): String {
            val basePackage = namespace.substringBefore(".feature")
             // namespace: com.example.bookpedia.feature.book.plugin.details
             // feature name: book
            val parts = namespace.split(".")
            val featureName = parts[parts.indexOf("feature") + 1]

            return """
                plugins {
                    id("$basePackage.android.feature")
                }
                
                android {
                    namespace = "$namespace"
                }

                dependencies {
                    implementation(project(":feature:$featureName:service"))
                }
            """.trimIndent()
        }

        fun createNewInstance(pluginWrapperPackage: PluginWrapperPackage, pluginName: String): PluginPackage? {
            val featurePackage = pluginWrapperPackage.featurePackage
            val appNamespace = featurePackage.rootPackage?.appPackage?.moduleGradle?.getRootPackage() ?: "com.example.app"
            
            // com.example.bookpedia.feature.book.plugin.details
            val packagePath = "${appNamespace}.feature.${featurePackage.featureName}.plugin.${pluginName.lowercase()}"

            // The module directory name is just the plugin name (e.g. bookdetails) 
            // but wrapped in 'plugin' folder?
            // Bookpedia structure: feature/book/plugin/bookdetails
            // The wrapper package is 'plugin'.
            
            return pluginWrapperPackage.createNewModule(
                moduleName = pluginName.lowercase(),
                packageName = packagePath,
                namespace = packagePath
            )?.let { PluginPackage(it) }
        }

        fun getInstance(pluginWrapperPackage: PluginWrapperPackage, pluginName: String): PluginPackage? {
            // pluginWrapperPackage file is .../feature/book/plugin
            // We want .../feature/book/plugin/<pluginName>
            
            val moduleDir = pluginWrapperPackage.file.findChild(pluginName.lowercase()) ?: return null
            val srcDir = moduleDir.findChild("src")?.findChild("main")?.findChild("java") ?: return null
            
            val featurePackage = pluginWrapperPackage.featurePackage
            val appNamespace = featurePackage.rootPackage?.appPackage?.moduleGradle?.getRootPackage() ?: return null
            // com.example.bookpedia.feature.book.plugin.bookdetails
            val packagePath = "${appNamespace}.feature.${featurePackage.featureName}.plugin.${pluginName.lowercase()}"
            
            var current = srcDir
            packagePath.split(".").forEach { 
                current = current?.findChild(it)
            }
            
            return current?.let { PluginPackage(it) }
        }
    }
}
