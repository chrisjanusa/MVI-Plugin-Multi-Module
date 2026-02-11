package com.github.chrisjanusa.mvi.package_structure.manager.old.feature

import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.instance_companion.ChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.api.ApiPackage
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.domain_model.DomainModelModule
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.domain_model.DomainModelPackage
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.nav.FeatureNavPackage
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.plugin.PluginWrapperPackage
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.service.FeatureServiceModule
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.service.FeatureServicePackage
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.SharedPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.FeatureChild
import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootChild
import com.intellij.openapi.vfs.VirtualFile

class FeaturePackage(file: VirtualFile) : PackageManager(file), RootChild {
    val featureName = name.toPascalCase()
    val featureWrapperPackage by lazy {
        FeatureWrapperPackage(file.parent)
    }
    override val rootPackage by lazy {
        featureWrapperPackage.rootPackage
    }

    val pluginWrapperPackage by lazy {
        file.findChild(PluginWrapperPackage.NAME)?.let { PluginWrapperPackage(it) }
    }
    val plugins by lazy {
        pluginWrapperPackage?.plugins ?: emptyList()
    }

    val domainModelPackage by lazy {
        DomainModelModule.getInstance(this)
    }
    val domainModels by lazy {
        domainModelPackage?.domainModels ?: emptyList()
    }

    val navPackage by lazy {
        file.findChild(FeatureNavPackage.NAME)?.let { FeatureNavPackage(it) }
    }

    val apiPackage by lazy {
        file.findChild(ApiPackage.NAME)?.let { ApiPackage(it) }
    }
    val apis by lazy {
        apiPackage?.apis ?: emptyList()
    }

    fun findAPI(repositoryName: String) = apiPackage?.findAPI(repositoryName)

    val servicePackage by lazy {
        FeatureServiceModule.getInstance(this)
    }
    val repositories by lazy {
        servicePackage?.repositories ?: emptyList()
    }

    val sharedPackage by lazy {
        file.findChild(SharedPackage.NAME)?.let { SharedPackage(it) }
    }

    fun createNavGraph() = FeatureNavPackage.createNewInstance(this)
    fun createServicePackage() = FeatureServicePackage.createNewInstance(this)
    fun createDomainModelPackage() = DomainModelPackage.createNewInstance(this)
    fun createSharedState() = SharedPackage.createNewInstance(this)
    fun createApiPackage() = ApiPackage.createNewInstance(this)

    private fun createAllChildren() {
        PluginWrapperPackage.createNewInstance(this)
    }

    companion object : ChildInstanceCompanion(
        FeatureWrapperPackage
    ) {
        override fun createInstance(virtualFile: VirtualFile) = FeaturePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(FeatureNavPackage, FeatureServicePackage, DomainModelPackage, PluginWrapperPackage, SharedPackage, ApiPackage)

        fun getFromManager(manager: Manager): FeaturePackage? {
            if (manager is FeaturePackage) return manager
            val featureChild = manager as? FeatureChild ?: return null
            return featureChild.featurePackage
        }

        fun createNewInstance(insertionPackage: FeatureWrapperPackage, featureName: String): FeaturePackage? {
            val featurePackage = insertionPackage.createNewDirectory(featureName)?.let { FeaturePackage(it) }
            featurePackage?.createAllChildren()
            return featurePackage
        }
    }
}