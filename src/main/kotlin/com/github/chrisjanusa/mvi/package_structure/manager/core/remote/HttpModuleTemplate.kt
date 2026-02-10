package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class HttpModuleTemplate(
    packageManager: Manager
) : Template(packageManager, HttpModuleFileManager.NAME) {
    override fun createContent(): String {
        return  "import android.content.Context\n" +
                "import dev.zacsweers.metro.AppScope\n" +
                "import dev.zacsweers.metro.ContributesTo\n" +
                "import dev.zacsweers.metro.Provides\n" +
                "import dev.zacsweers.metro.SingleIn\n" +
                "import io.ktor.client.HttpClient\n" +
                "import io.ktor.client.engine.HttpClientEngine\n" +
                "import io.ktor.client.engine.okhttp.OkHttp\n" +
                "\n" +
                "@ContributesTo(AppScope::class)\n" +
                "interface HttpModule {\n" +
                "    @Provides\n" +
                "    @SingleIn(AppScope::class)\n" +
                "    fun provideHttpEngine(context: Context): HttpClientEngine = OkHttp.create()\n" +
                "\n" +
                "    @Provides\n" +
                "    @SingleIn(AppScope::class)\n" +
                "    fun provideHttpClient(engine: HttpClientEngine): HttpClient = HttpClientFactory.create(engine)\n" +
                "}"
    }
}
