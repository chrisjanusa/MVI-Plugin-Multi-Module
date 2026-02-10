package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.domain_model

import com.github.chrisjanusa.mvi.package_structure.manager.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.FeaturePackage
import com.intellij.openapi.vfs.VirtualFile

class DomainModelModule(file: VirtualFile) : PackageManager(file) {

    companion object : ModuleInstanceCompanion() {
        private const val MODULE_NAME = "domain"

        override fun isInstance(file: VirtualFile?): Boolean = false 

        override fun createInstance(virtualFile: VirtualFile): Manager? = null 

        override fun getBuildGradleTemplate(namespace: String): String {
            val validNamespace = namespace.substringBeforeLast(".") 
            val basePackage = namespace.substringBefore(".feature")

            return """
                plugins {
                    id("$basePackage.android.library")
                }
                
                android {
                    namespace = "$namespace"
                }

                dependencies {
                    implementation(project(":common:service:model"))
                    implementation(libs.kotlinx.coroutines.core)
                }
            """.trimIndent()
        }

        fun createNewInstance(featurePackage: FeaturePackage): DomainModelPackage? {
            val appNamespace = featurePackage.rootPackage?.appPackage?.moduleGradle?.getRootPackage() ?: "com.example.app"
            // com.example.bookpedia.feature.book.domain
            val packagePath = "${appNamespace}.feature.${featurePackage.featureName}.$MODULE_NAME"
            
            return featurePackage.createNewModule(
                moduleName = MODULE_NAME,
                packageName = packagePath,
                namespace = packagePath
            )?.let { DomainModelPackage(it) }
        }

        fun getInstance(featurePackage: FeaturePackage): DomainModelPackage? {
            val moduleDir = featurePackage.file.findChild(MODULE_NAME) ?: return null
            val srcDir = moduleDir.findChild("src")?.findChild("main")?.findChild("java") ?: return null
            
            // Need to traverse down to the package
            // Package: com.example.bookpedia.feature.book.domain
            // But we don't know the app namespace for sure without expensive lookup?
            // Heuristic: Just find the leaf/deepest directory? 
            // Or use the known structure.
            
            val appNamespace = featurePackage.rootPackage?.appPackage?.moduleGradle?.getRootPackage() ?: return null
            val packagePath = "${appNamespace}.feature.${featurePackage.featureName}.$MODULE_NAME"
            
            var current = srcDir
            packagePath.split(".").forEach { 
                current = current?.findChild(it)
            }
            
            return current?.let { DomainModelPackage(it) }
        }
    }
}
