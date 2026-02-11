package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class ErrorTemplate(
    packageManager: Manager
) : Template(packageManager, ErrorFileManager.NAME) {
    override fun createContent(): String {
        return "interface Error"
    }
}
