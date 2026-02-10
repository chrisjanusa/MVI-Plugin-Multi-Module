package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

internal class AppApplicationTemplate(
    val appPackage: AppPackage,
    fileName: String
) : Template(
    packageManager = appPackage,
    fileName = fileName
) {

    override fun createContent(): String =
        "import android.app.Application\n" +
        "import ${appPackage.rootPackage.diPackage?.metroAppGraph?.packagePath}\n" +
        "import dev.zacsweers.metro.createGraphFactory\n" +
        "import dev.zacsweers.metrox.android.MetroAppComponentProviders\n" +
        "import dev.zacsweers.metrox.android.MetroApplication\n" +
        "\n" +
        "class BookApplication: Application(), MetroApplication {\n" +
        "    private val appGraph by lazy { createGraphFactory<MetroAppGraph.Factory>().create(this) }\n" +
        "\n" +
        "    override val appComponentProviders: MetroAppComponentProviders\n" +
        "        get() = appGraph\n" +
        "}"
}