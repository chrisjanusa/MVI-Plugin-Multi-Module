package com.github.chrisjanusa.mvi.package_structure.manager.module

import com.github.chrisjanusa.mvi.helper.file_helper.findChildFile
import com.github.chrisjanusa.mvi.helper.file_helper.createNewDirectory
import com.github.chrisjanusa.mvi.package_structure.manager.PackageManager
import com.github.chrisjanusa.mvi.package_structure.manager.app.AppModule.Companion.createNewDirectory
import com.intellij.openapi.vfs.VirtualFile
import kotlinx.html.InputType

abstract class ModuleManager(file: VirtualFile) : PackageManager(file) {
    abstract val moduleGradle : ModuleGradleManager?

    private val srcPackage by lazy {
        file.findChildFile(SRC_PACKAGE_NAME)
    }

    private val unitTestWrapperPackage by lazy {
        srcPackage?.findChildFile(UNIT_TEST_PACKAGE_NAME)
    }
    private val codeWrapperPackage by lazy {
        srcPackage?.findChildFile(CODE_PACKAGE_NAME)
    }
    private val androidTestWrapperPackage by lazy {
        srcPackage?.findChildFile(ANDROID_TEST_PACKAGE_NAME)
    }

    fun deleteUnitTestPackage() {
        deleteDirectory(UNIT_TEST_PACKAGE_NAME)
    }

    fun deleteAndroidTestPackage() {
        deleteDirectory(ANDROID_TEST_PACKAGE_NAME)
    }

    val unitTestPackageFile by lazy {
        unitTestWrapperPackage?.findChildFile(JAVA_PACKAGE_NAME)
            ?.navigateDownPackagePath(moduleGradle?.getRootPackage())
    }

    val codePackageFile by lazy {
        codeWrapperPackage?.findChildFile(JAVA_PACKAGE_NAME)?.navigateDownPackagePath(moduleGradle?.getRootPackage())
    }

    val androidTestPackageFile by lazy {
        unitTestPackageFile?.findChildFile(JAVA_PACKAGE_NAME)?.navigateDownPackagePath(moduleGradle?.getRootPackage())
    }

    fun createUnitTestPackage() =
        createNewDirectory(SRC_PACKAGE_NAME)
            ?.createNewDirectory(JAVA_PACKAGE_NAME)
            ?.createNewDirectory(UNIT_TEST_PACKAGE_NAME)
            ?.createDownPackagePath(moduleGradle?.getRootPackage())

    fun createCodePackage() =
        createNewDirectory(SRC_PACKAGE_NAME)
            ?.createNewDirectory(JAVA_PACKAGE_NAME)
            ?.createNewDirectory(CODE_PACKAGE_NAME)
            ?.createDownPackagePath(moduleGradle?.getRootPackage())

    fun createAndroidTestPackage() =
        createNewDirectory(SRC_PACKAGE_NAME)
            ?.createNewDirectory(JAVA_PACKAGE_NAME)
            ?.createNewDirectory(ANDROID_TEST_PACKAGE_NAME)
            ?.createDownPackagePath(moduleGradle?.getRootPackage())

    private fun VirtualFile.navigateDownPackagePath(packagePath: String?): VirtualFile? {
        packagePath ?: return null
        var currFile: VirtualFile? = this
        packagePath.split(".").forEach { childName ->
            currFile = currFile?.findChildFile(childName)
        }
        return currFile
    }

    private fun VirtualFile.createDownPackagePath(packagePath: String?): VirtualFile? {
        packagePath ?: return null
        var currFile: VirtualFile? = this
        packagePath.split(".").forEach { childName ->
            currFile = currFile?.createNewDirectory(childName)
        }
        return currFile
    }

    override fun toString(): String {
        return InputType.file.name
    }

    companion object {
        private const val UNIT_TEST_PACKAGE_NAME = "test"
        private const val ANDROID_TEST_PACKAGE_NAME = "androidTest"
        private const val CODE_PACKAGE_NAME = "main"
        private const val JAVA_PACKAGE_NAME = "java"
        private const val SRC_PACKAGE_NAME = "src"
    }
}