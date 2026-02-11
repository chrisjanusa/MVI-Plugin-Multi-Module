package com.github.chrisjanusa.mvi.helper.file_helper

import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import com.intellij.util.ThrowableRunnable

internal fun VirtualFile.createNewDirectory(name: String): VirtualFile? {
    val existingDirectory = findChild(name)
    if (existingDirectory != null) return existingDirectory

    var dirFile: VirtualFile? = null
    WriteAction.runAndWait(ThrowableRunnable {
        dirFile = createChildDirectory(this, name)
    })
    return dirFile
}

internal fun PackageManager.createNewDirectory(name: String): VirtualFile? = file.createNewDirectory(name)

internal fun VirtualFile.createNewFileWithExtension(name: String, extension: Extension, content: String): VirtualFile? {
    val fullFileName = "$name.${extension.extension}"
    val existingFile = findChild(fullFileName)
    if (existingFile != null) return existingFile
    val project = getProject() ?: return null
    val directory = PsiManager.getInstance(project).findDirectory(this)
    var file: PsiFile? = null
    WriteAction.runAndWait(ThrowableRunnable {
        file =
            PsiFileFactory.getInstance(project).createFileFromText(
                fullFileName,
                KotlinFileType,
                content
            )
        file?.let {
            file = directory?.add(it)?.containingFile
        }
    })
    return file?.virtualFile
}

internal fun PackageManager.createNewFileWithExtension(name: String, extension: Extension, content: String): VirtualFile? {
    return file.createNewFileWithExtension(name, extension, content)
}

internal fun PackageManager.createNewFile(name: String, content: String): VirtualFile? {
    val contentWithPackage = "package ${packagePath}\n\n${content}"
    return file.createNewFileWithExtension(name, Extension.Kotlin, contentWithPackage)
}

internal sealed class Extension(val extension: String) {
    data object Xml : Extension("xml")
    data object Kts : Extension("kts")
    data object Kotlin : Extension("kt")
}