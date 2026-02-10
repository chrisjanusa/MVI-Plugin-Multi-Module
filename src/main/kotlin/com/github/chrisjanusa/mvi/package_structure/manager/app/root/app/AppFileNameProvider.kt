package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.package_structure.instance_companion.StaticSuffixChildInstanceCompanion

abstract class AppFileNameProvider(suffix: String) : StaticSuffixChildInstanceCompanion(
    suffix,
    AppPackage
) {
    fun getFileName(appName: String): String = "$appName$SUFFIX"
}