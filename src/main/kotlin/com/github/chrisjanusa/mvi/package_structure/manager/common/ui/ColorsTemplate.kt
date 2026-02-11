package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class ColorsTemplate(
    packageManager: Manager
) : Template(packageManager, ColorsFileManager.NAME) {
    override fun createContent(): String {
        return "import androidx.compose.ui.graphics.Color\n" +
                "\n" +
                "val DarkBlue = Color(0xFF0B405E)\n" +
                "val DesertWhite = Color(0xFFF7F7F7)\n" +
                "val SandYellow = Color(0xFFFFBD64)\n" +
                "val LightBlue = Color(0xFF9AD9FF)"
    }
}
