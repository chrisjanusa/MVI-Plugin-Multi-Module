package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.service

import com.github.chrisjanusa.mvi.package_structure.manager.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.FeaturePackage
import com.intellij.openapi.vfs.VirtualFile

class FeatureServiceModule(file: VirtualFile) : PackageManager(file) {

    companion object : ModuleInstanceCompanion() {
        override fun isInstance(file: VirtualFile?): Boolean = false 

        override fun createInstance(virtualFile: VirtualFile): Manager? = null 

        override fun getBuildGradleTemplate(namespace: String): String {
            val pluginPrefix = namespace.substringBeforeLast(".feature") // e.g. com.example.bookpedia -> com.example
            // Heuristic for prefix: namespace is com.example.bookpedia.feature.book.service
            // We want com.example.android.library
            // So we need to strip .feature... and then take the base package
            val basePackage = namespace.substringBefore(".feature")
            
            return """
                plugins {
                    id("$basePackage.android.library")
                    id("$basePackage.android.metro")
                    // Room plugin will be added dynamically by DatabaseAction if needed
                }
                
                android {
                    namespace = "$namespace"
                }

                dependencies {
                    implementation(project(":common:service:model"))
                    implementation(project(":common:service:database"))
                    implementation(project(":common:service:remote"))
                    implementation(project(":feature:${namespace.split(".")[4]}:domain")) // feature:book:domain
                    implementation(project(":common:testing"))
                    
                    implementation(libs.kotlinx.coroutines.core)
                    implementation(libs.ktor.client.core)
                    implementation(libs.androidx.room.runtime)
                    implementation(libs.metro.runtime)
                }
            """.trimIndent()
        }

        fun createNewInstance(featurePackage: FeaturePackage): FeatureServicePackage? {
            val appNamespace = featurePackage.rootPackage?.appPackage?.moduleGradle?.getRootPackage() ?: "com.example.app"
            val packagePath = "${appNamespace}.feature.${featurePackage.featureName}.service"

            return featurePackage.createNewModule(
                moduleName = "service",
                packageName = packagePath,
                namespace = packagePath
            )?.let { FeatureServicePackage(it) }
        }

        fun getInstance(featurePackage: FeaturePackage): FeatureServicePackage? {
            val moduleDir = featurePackage.file.findChild("service") ?: return null
            val srcDir = moduleDir.findChild("src")?.findChild("main")?.findChild("java") ?: return null
            
            val appNamespace = featurePackage.rootPackage?.appPackage?.moduleGradle?.getRootPackage() ?: return null
            val packagePath = "${appNamespace}.feature.${featurePackage.featureName}.service"
            
            var current = srcDir
            packagePath.split(".").forEach { 
                current = current?.findChild(it)
            }
            
            return current?.let { FeatureServicePackage(it) }
        }
    }
}
