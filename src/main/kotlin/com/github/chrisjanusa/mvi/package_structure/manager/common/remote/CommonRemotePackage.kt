package com.github.chrisjanusa.mvi.package_structure.manager.common.remote

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.toPascalCase
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticChildInstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.common.CommonPackage
import com.intellij.openapi.vfs.VirtualFile

class CommonRemotePackage(file: VirtualFile): PackageManager(file) {
    val featureName = name.toPascalCase()
    val commonPackage by lazy {
        CommonPackage(file.parent.parent.parent)
    }

    val httpResponseHelper by lazy {
        file.findChildFile(HttpResponseHelperFileManager.NAME)?.let { HttpResponseHelperFileManager(it) }
    }

    private fun createAllChildren() {
        HttpResponseHelperFileManager.createNewInstance(this)
    }

    companion object : StaticChildInstanceCompanion("remote", CommonRemoteModule.Companion) {
        override fun createInstance(virtualFile: VirtualFile) = CommonRemotePackage(virtualFile)

        override val allChildrenInstanceCompanions: List<InstanceCompanion>
            get() = listOf(HttpResponseHelperFileManager.Companion)

        fun createNewInstance(insertionModule: CommonRemoteModule): CommonRemotePackage? {
            val packageManager = insertionModule.createCodePackage()?.let { CommonRemotePackage(it) }
            packageManager?.createAllChildren()
            return packageManager
        }
    }
}
