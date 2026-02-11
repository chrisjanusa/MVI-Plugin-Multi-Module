package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.base.FileManager
import com.intellij.openapi.vfs.VirtualFile

class NavGraphContributorFileManager(file: VirtualFile) : FileManager(file) {
    companion object : StaticChildInstanceCompanion("NavGraphContributor", CoreNavPackage.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = NavGraphContributorFileManager(virtualFile)

        fun createNewInstance(insertionPackage: CoreNavPackage): NavGraphContributorFileManager? {
            return insertionPackage.createNewFile(
                NAME,
                NavGraphContributorTemplate(insertionPackage).createContent()
            )?.let { NavGraphContributorFileManager(it) }
        }
    }
}
