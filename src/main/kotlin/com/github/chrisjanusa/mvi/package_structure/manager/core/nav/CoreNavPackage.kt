package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.core.CorePackage
import com.intellij.openapi.vfs.VirtualFile

class CoreNavPackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()

    val navAction by lazy {
        file.findChild(CoreNavActionFileManager.NAME)?.let { CoreNavActionFileManager(it) }
    }

    val navComponentId by lazy {
        file.findChild(NavComponentIdFileManager.NAME)?.let { NavComponentIdFileManager(it) }
    }

    val navGraphContributor by lazy {
        file.findChild(NavGraphContributorFileManager.NAME)?.let { NavGraphContributorFileManager(it) }
    }

    private fun createAllChildren() {
        CoreNavActionFileManager.createNewInstance(this)
        NavComponentIdFileManager.createNewInstance(this)
        NavGraphContributorFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("nav", CorePackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CoreNavPackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(
                CoreNavActionFileManager.Companion,
                NavComponentIdFileManager.Companion,
                NavGraphContributorFileManager.Companion
            )

        fun createNewInstance(insertionModule: CoreNavModule): CoreNavPackage? {
            val packageManager = insertionModule.createCodePackage()?.let { CoreNavPackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
