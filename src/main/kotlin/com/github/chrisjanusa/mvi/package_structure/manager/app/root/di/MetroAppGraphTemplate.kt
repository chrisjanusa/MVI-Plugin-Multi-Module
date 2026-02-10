package com.github.chrisjanusa.mvi.package_structure.manager.app.root.di

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.Manager

internal class MetroAppGraphTemplate(
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String =
                "import android.app.Application\n" +
                "import android.content.Context\n" +
                "import ${projectPackage?.foundationModule?.foundationPackage?.appEffect?.packagePath}\n" +
                // TODO: core/nav package NavGraphContributor "import ${}\n" +
                "import dev.zacsweers.metro.AppScope\n" +
                "import dev.zacsweers.metro.DependencyGraph\n" +
                "import dev.zacsweers.metro.Provides\n" +
                "import dev.zacsweers.metrox.android.MetroAppComponentProviders\n" +
                "import dev.zacsweers.metrox.viewmodel.ViewModelGraph\n" +
                "\n" +
                "@DependencyGraph(AppScope::class)\n" +
                "interface MetroAppGraph : MetroAppComponentProviders, ViewModelGraph {\n" +
                "    val navContributors: Set<NavGraphContributor>\n" +
                "    val appEffects: Set<AppEffect>\n" +
                "\n" +
                "    @Provides fun provideApplicationContext(application: Application): Context = application\n" +
                "\n" +
                "    @DependencyGraph.Factory\n" +
                "    fun interface Factory {\n" +
                "        fun create(@Provides application: Application): MetroAppGraph\n" +
                "    }\n" +
                "}"
}