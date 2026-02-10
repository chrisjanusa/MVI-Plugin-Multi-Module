package com.github.chrisjanusa.mvi.package_structure.manager.core.nav

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class NavComponentIdTemplate(
    packageManager: Manager,
) : Template(packageManager, NavComponentIdFileManager.NAME) {
    override fun createContent(): String {
        return "interface $fileName"
    }
}
