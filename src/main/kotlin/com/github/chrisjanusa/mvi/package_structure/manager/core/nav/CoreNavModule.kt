package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.core.CorePackage
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile

class CoreNavModule(file: VirtualFile): ModuleManager(file) {
    val coreNavPackage by lazy {
        codePackageFile?.let { CoreNavPackage(it) }
    }

    override val moduleGradle: ModuleGradleManager? by lazy {
        file.findChild(CoreNavModuleGradleManager.NAME)?.let { CoreNavModuleGradleManager(it) }
    }

    private fun createAllChildren() {
        CoreNavModuleGradleManager.createNewInstance(this)
        CoreNavPackage.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("nav", CorePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreNavModule(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(CoreNavModuleGradleManager.Companion)

        fun createNewInstance(insertionPackage: CorePackage): CoreNavModule? {
            val packageManager = insertionPackage.createNewDirectory(NAME)?.let { CoreNavModule(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}