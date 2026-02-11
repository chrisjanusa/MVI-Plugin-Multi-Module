package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class UiTextTemplate(
    packageManager: Manager
) : Template(packageManager, UiTextFileManager.NAME) {
    override fun createContent(): String {
        return "import androidx.compose.runtime.Composable\n" +
                "import androidx.compose.ui.res.stringResource\n" +
                "\n" +
                "\n" +
                "sealed interface UiText {\n" +
                "    data class DynamicString(val value: String): UiText\n" +
                "    class StringResourceId(\n" +
                "        val id: Int,\n" +
                "        val args: Array<Any> = arrayOf()\n" +
                "    ): UiText\n" +
                "\n" +
                "    @Composable\n" +
                "    fun asString(): String {\n" +
                "        return when(this) {\n" +
                "            is DynamicString -> value\n" +
                "            is StringResourceId -> stringResource(id = id, formatArgs = args)\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
