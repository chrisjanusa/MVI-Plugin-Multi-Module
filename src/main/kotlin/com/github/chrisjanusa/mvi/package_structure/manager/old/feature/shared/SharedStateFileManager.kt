package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared

import com.github.chrisjanusa.mvi.helper.file_helper.createNewFile
import com.github.chrisjanusa.mvi.package_structure.manager.base.StateFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.helper.ISharedFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.helper.SharedFileManager
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.helper.SharedFileNameProvider
import com.github.chrisjanusa.mvi.package_structure.parent_provider.FeatureChild
import com.intellij.openapi.vfs.VirtualFile

class SharedStateFileManager(
    file: VirtualFile,
    sharedFileManager: ISharedFileManager = SharedFileManager(file)
) : StateFileManager(file), ISharedFileManager by sharedFileManager, FeatureChild {

    companion object : SharedFileNameProvider(SUFFIX) {
        override fun createInstance(virtualFile: VirtualFile) = SharedStateFileManager(virtualFile)
        fun createNewInstance(insertionPackage: SharedPackage): SharedStateFileManager? {
            val fileName = getFileName(insertionPackage.featureName)
            return insertionPackage.createNewFile(
                fileName,
                SharedStateTemplate(insertionPackage, fileName)
                    .createContent()
            )?.let { SharedStateFileManager(it) }
        }
    }
}