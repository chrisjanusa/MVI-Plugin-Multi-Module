package com.github.chrisjanusa.mvi.package_structure.manager.common.ui

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class BackButtonTemplate(
    packageManager: Manager
) : Template(packageManager, BackButtonFileManager.NAME) {
    override fun createContent(): String {
        return "import androidx.compose.material.icons.Icons\n" +
                "import androidx.compose.material.icons.automirrored.filled.ArrowBack\n" +
                "import androidx.compose.material3.Icon\n" +
                "import androidx.compose.material3.IconButton\n" +
                "import androidx.compose.runtime.Composable\n" +
                "import androidx.compose.ui.Modifier\n" +
                "import androidx.compose.ui.graphics.Color\n" +
                "import androidx.compose.ui.graphics.vector.ImageVector\n" +
                "\n" +
                "@Composable\n" +
                "fun BackButton(\n" +
                "    modifier: Modifier = Modifier,\n" +
                "    onClick: () -> Unit,\n" +
                "    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,\n" +
                "    iconTint: Color = Color.White\n" +
                ") {\n" +
                "    IconButton(\n" +
                "        onClick = onClick,\n" +
                "        modifier = modifier\n" +
                "    ) {\n" +
                "        Icon(\n" +
                "            imageVector = icon,\n" +
                "            contentDescription = null,\n" +
                "            tint = iconTint\n" +
                "        )\n" +
                "    }\n" +
                "}"
    }
}
