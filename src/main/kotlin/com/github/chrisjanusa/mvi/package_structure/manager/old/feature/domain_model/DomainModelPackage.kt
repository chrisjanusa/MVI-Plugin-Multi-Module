package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.domain_model

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.FeaturePackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.FeatureDirectChild
import com.intellij.openapi.vfs.VirtualFile

class DomainModelPackage(file: VirtualFile): PackageManager(file), FeatureDirectChild {
    override val featurePackage by lazy {
        val moduleRootPath = file.path.substringBefore("/src/main/java")
        val moduleRoot = file.fileSystem.findFileByPath(moduleRootPath)
        FeaturePackage(moduleRoot!!.parent)
    }
    val featureName by lazy {
        featurePackage.featureName
    }
    override val rootPackage by lazy {
        featurePackage.rootPackage
    }

    val domainModels by lazy {
        file.children.map {
            DomainModelFileManager(it)
        }
    }

    fun createDomainModel(modelName: String) = DomainModelFileManager.createNewInstance(this, modelName)

    companion object : StaticInstanceCompanion("domain") {
        override fun createInstance(virtualFile: VirtualFile) = DomainModelPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(DomainModelFileManager)

        fun createNewInstance(insertionPackage: FeaturePackage): DomainModelPackage? {
            return DomainModelModule.createNewInstance(insertionPackage)
        }
    }
}