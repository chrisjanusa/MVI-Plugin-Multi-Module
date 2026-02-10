package com.github.chrisjanusa.mvi.package_structure.manager.base

import com.github.chrisjanusa.mvi.helper.file_helper.getProject
import com.github.chrisjanusa.mvi.helper.file_helper.getProjectPackage
import com.github.chrisjanusa.mvi.package_structure.Manager

abstract class Template(
    internal val packageManager: Manager,
    internal val fileName: String
) {
    internal val projectPackage = packageManager.file.getProject()?.getProjectPackage()

    abstract fun createContent(): String
}