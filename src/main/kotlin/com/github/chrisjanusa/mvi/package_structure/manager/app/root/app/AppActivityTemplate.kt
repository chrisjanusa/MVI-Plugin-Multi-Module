package com.github.chrisjanusa.mvi.package_structure.manager.app.root.app

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

internal class AppActivityTemplate(
    val appPackage: AppPackage,
    fileName: String
) : Template(
    packageManager = appPackage,
    fileName = fileName
) {
    val appName = fileName.substringBefore(AppActivityFileManager.SUFFIX)

    override fun createContent(): String =
        "import android.app.Activity\n" +
        "import android.os.Bundle\n" +
        "import androidx.activity.ComponentActivity\n" +
        "import androidx.activity.compose.setContent\n" +
        "import androidx.activity.enableEdgeToEdge\n" +
        "import androidx.compose.foundation.layout.PaddingValues\n" +
        "import androidx.compose.foundation.layout.fillMaxSize\n" +
        "import androidx.compose.foundation.layout.padding\n" +
        "import androidx.compose.material3.Scaffold\n" +
        "import androidx.compose.runtime.Composable\n" +
        "import androidx.compose.runtime.CompositionLocalProvider\n" +
        "import androidx.compose.runtime.LaunchedEffect\n" +
        "import androidx.compose.ui.Modifier\n" +
        "import androidx.navigation.NavHostController\n" +
        "import androidx.navigation.compose.NavHost\n" +
        "import androidx.navigation.compose.rememberNavController\n" +
        "import ${appPackage.rootPackage.diPackage?.localAppGraph?.packagePath}\n" +
        "import ${appPackage.rootPackage.diPackage?.metroAppGraph?.packagePath}\n" +
        "import ${appPackage.rootPackage.themePackage?.packagePath}.${appName}Theme\n" +
        "import ${projectPackage?.corePackage?.navModule?.coreNavPackage?.navAction?.packagePath}\n" +
        "import ${projectPackage?.corePackage?.navModule?.coreNavPackage?.navGraphContributor?.packagePath}\n" +
        "import dev.zacsweers.metro.AppScope\n" +
        "import dev.zacsweers.metro.ContributesIntoMap\n" +
        "import dev.zacsweers.metro.Inject\n" +
        "import dev.zacsweers.metro.binding\n" +
        "import dev.zacsweers.metro.createGraphFactory\n" +
        "import dev.zacsweers.metrox.android.ActivityKey\n" +
        "import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory\n" +
        "import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory\n" +
        "import dev.zacsweers.metrox.viewmodel.metroViewModel\n" +
        "\n" +
        "@ContributesIntoMap(AppScope::class, binding<Activity>())\n" +
        "@ActivityKey(MainActivity::class)\n" +
        "@Inject\n" +
        "class MainActivity(private val metroVmf: MetroViewModelFactory) : ComponentActivity() {\n" +
        "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
        "        super.onCreate(savedInstanceState)\n" +
        "        enableEdgeToEdge()\n" +
        "        val graph = createGraphFactory<MetroAppGraph.Factory>().create(application)\n" +
        "        setContent {\n" +
        "            CompositionLocalProvider(\n" +
        "                LocalMetroViewModelFactory provides metroVmf,\n" +
        "                LocalAppGraph provides graph\n" +
        "            ) {\n" +
        "            ${appName}Theme {\n" +
        "                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->\n" +
        "                    val navController = rememberNavController()\n" +
        "                    AppNavigation(innerPadding, navController, graph.navContributors)\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    }\n" +
        "\n" +
        "    @Composable\n" +
        "    fun AppNavigation(\n" +
        "        innerPadding: PaddingValues,\n" +
        "        navController: NavHostController,\n" +
        "        navContributors: Set<NavGraphContributor>,\n" +
        "        viewModel: AppViewModel = metroViewModel(),\n" +
        "    ) {\n" +
        "        LaunchedEffect(Unit) {\n" +
        "            viewModel.navActionFlow.collect {\n" +
        "                when (it) {\n" +
        "                    is CoreNavAction.OnNavigateToScreen -> {\n" +
        "                        navController.navigate(it.navComponent)\n" +
        "                    }\n" +
        "                    is CoreNavAction.OnBackClick -> {\n" +
        "                        navController.navigateUp()\n" +
        "                    }\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "        NavHost(\n" +
        "            modifier = Modifier.padding(innerPadding),\n" +
        "            navController = navController,\n" +
        "            startDestination = BookListNavComponentId\n" +
        "        ) {\n" +
        "            navContributors.forEach { contributor ->\n" +
        "                contributor.register(this, viewModel::onAction)\n" +
        "            }\n" +
        "        }\n" +
        "    }\n" +
        "}"
}