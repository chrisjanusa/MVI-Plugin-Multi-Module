package com.github.chrisjanusa.mvi.package_structure.manager.app.root.di

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template
import com.github.chrisjanusa.mvi.package_structure.Manager

internal class MetroViewModelFactoryTemplate(
    packageManager: Manager,
    fileName: String
) : Template(
    packageManager = packageManager,
    fileName = fileName
) {

    override fun createContent(): String =
                "import androidx.lifecycle.ViewModel\n" +
                "import dev.zacsweers.metro.AppScope\n" +
                "import dev.zacsweers.metro.ContributesBinding\n" +
                "import dev.zacsweers.metro.Inject\n" +
                "import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory\n" +
                "import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory\n" +
                "import dev.zacsweers.metrox.viewmodel.ViewModelAssistedFactory\n" +
                "import dev.zacsweers.metro.Provider\n" +
                "import dev.zacsweers.metro.SingleIn\n" +
                "import kotlin.reflect.KClass\n" +
                "\n" +
                "@Inject\n" +
                "@ContributesBinding(AppScope::class)\n" +
                "@SingleIn(AppScope::class)\n" +
                "class MetroViewModelFactory(\n" +
                "    override val viewModelProviders: Map<KClass<out ViewModel>, Provider<ViewModel>>,\n" +
                "    override val assistedFactoryProviders: Map<KClass<out ViewModel>, Provider<ViewModelAssistedFactory>>,\n" +
                "    override val manualAssistedFactoryProviders: Map<KClass<out ManualViewModelAssistedFactory>, Provider<ManualViewModelAssistedFactory>>,\n" +
                ") : MetroViewModelFactory()"
}