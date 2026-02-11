package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.helper.file_helper.Extension
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.findChildFileWithExtension
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.common.remote.CommonRemoteModuleGradleManager
import com.github.chrisjanusa.mvi.package_structure.manager.core.CorePackage
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleManager
import com.github.chrisjanusa.mvi.package_structure.manager.module.ModuleGradleManager
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.rd.util.threading.coroutines.RdCoroutineScope.Companion.override

class CoreNavModule(file: VirtualFile): ModuleManager(file) {
    val coreNavPackage by lazy {
        codePackageFile?.let { CoreNavPackage(it) }
    }

    override val moduleGradle by lazy {
        file.findChildFileWithExtension(CoreNavModuleGradleManager.NAME, Extension.Kts)?.let { CoreNavModuleGradleManager(it) }
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