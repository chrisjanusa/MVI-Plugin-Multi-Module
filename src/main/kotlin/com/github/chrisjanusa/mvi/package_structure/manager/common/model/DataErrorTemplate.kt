package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class DataErrorTemplate(
    packageManager: Manager
) : Template(packageManager, DataErrorFileManager.NAME) {
    override fun createContent(): String {
        return "sealed interface DataError: Error {\n" +
                "    enum class Remote : DataError {\n" +
                "        REQUEST_TIME_OUT,\n" +
                "        TOO_MANY_REQUESTS,\n" +
                "        NO_INTERNET,\n" +
                "        SERVER_ERROR,\n" +
                "        SERIALIZATION,\n" +
                "        UNKNOWN\n" +
                "    }\n" +
                "\n" +
                "    enum class Local : DataError {\n" +
                "        DISK_FULL\n" +
                "    }\n" +
                "}"
    }
}
