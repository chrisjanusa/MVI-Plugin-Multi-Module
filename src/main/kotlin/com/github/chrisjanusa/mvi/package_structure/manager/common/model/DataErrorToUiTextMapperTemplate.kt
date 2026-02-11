package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class DataErrorToUiTextMapperTemplate(
    packageManager: Manager
) : Template(packageManager, DataErrorToUiTextMapperFileManager.NAME) {
    override fun createContent(): String {
        return "import ${projectPackage?.commonPackage?.uiModule?.commonUiPackage?.uiText}\n" +
                "\n" +
                "fun DataError.toUiText() = when (this) {\n" +
                "    // TODO move these to string resources\n" +
                "    DataError.Remote.REQUEST_TIME_OUT -> UiText.DynamicString(\"Request timed out\")\n" +
                "    DataError.Remote.TOO_MANY_REQUESTS -> UiText.DynamicString(\"Too many requests in short period of time\")\n" +
                "    DataError.Remote.NO_INTERNET -> UiText.DynamicString(\"No internet connection\")\n" +
                "    DataError.Remote.SERVER_ERROR -> UiText.DynamicString(\"Server having trouble\")\n" +
                "    DataError.Remote.SERIALIZATION -> UiText.DynamicString(\"Serialization Error\")\n" +
                "    DataError.Local.DISK_FULL -> UiText.DynamicString(\"Disk Full Error\")\n" +
                "    DataError.Remote.UNKNOWN -> UiText.DynamicString(\"Unknown Error\")\n" +
                "}"
    }
}
