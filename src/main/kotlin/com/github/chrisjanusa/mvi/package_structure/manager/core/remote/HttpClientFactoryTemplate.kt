package com.github.chrisjanusa.mvi.package_structure.manager.core.remote

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class HttpClientFactoryTemplate(
    packageManager: Manager
) : Template(packageManager, HttpClientFactoryFileManager.NAME) {
    override fun createContent(): String {
        return  "import io.ktor.client.HttpClient\n" +
                "import io.ktor.client.engine.HttpClientEngine\n" +
                "import io.ktor.client.plugins.HttpTimeout\n" +
                "import io.ktor.client.plugins.contentnegotiation.ContentNegotiation\n" +
                "import io.ktor.client.plugins.defaultRequest\n" +
                "import io.ktor.client.plugins.logging.LogLevel\n" +
                "import io.ktor.client.plugins.logging.Logger\n" +
                "import io.ktor.client.plugins.logging.Logging\n" +
                "import io.ktor.http.ContentType\n" +
                "import io.ktor.http.contentType\n" +
                "import io.ktor.serialization.kotlinx.json.json\n" +
                "import kotlinx.serialization.json.Json\n" +
                "\n" +
                "object HttpClientFactory {\n" +
                "    fun create(engine: HttpClientEngine): HttpClient {\n" +
                "        return HttpClient(engine) {\n" +
                "            install(ContentNegotiation) {\n" +
                "                json(\n" +
                "                    json = Json {\n" +
                "                        ignoreUnknownKeys = true\n" +
                "                    }\n" +
                "                )\n" +
                "            }\n" +
                "            install(HttpTimeout) {\n" +
                "                socketTimeoutMillis = 20000\n" +
                "                requestTimeoutMillis = 20000\n" +
                "            }\n" +
                "            // Should replace with remote analytics event in a production app\n" +
                "            install(Logging) {\n" +
                "                logger = object : Logger {\n" +
                "                    override fun log(message: String) {\n" +
                "                        println(message)\n" +
                "                    }\n" +
                "                }\n" +
                "                level = LogLevel.ALL\n" +
                "            }\n" +
                "            defaultRequest {\n" +
                "                contentType(ContentType.Application.Json)\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
