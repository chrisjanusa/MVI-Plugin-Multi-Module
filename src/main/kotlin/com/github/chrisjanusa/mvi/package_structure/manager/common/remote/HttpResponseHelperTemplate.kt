package com.github.chrisjanusa.mvi.package_structure.manager.common.remote

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class HttpResponseHelperTemplate(
    packageManager: Manager
) : Template(packageManager, HttpResponseHelperFileManager.NAME) {
    override fun createContent(): String {
        val modelPackage = projectPackage?.commonPackage?.modelModule?.commonModelPackage
        return "import ${modelPackage?.dataError?.packagePath}\n" +
                "import ${modelPackage?.serviceResult?.packagePath}\n" +
                "import io.ktor.client.call.NoTransformationFoundException\n" +
                "import io.ktor.client.call.body\n" +
                "import io.ktor.client.statement.HttpResponse\n" +
                "import io.ktor.client.network.sockets.SocketTimeoutException\n" +
                "import io.ktor.util.network.UnresolvedAddressException\n" +
                "import kotlinx.coroutines.currentCoroutineContext\n" +
                "import kotlinx.coroutines.ensureActive\n" +
                "\n" +
                "suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): ServiceResult<T, DataError.Remote> {\n" +
                "    val response = try {\n" +
                "        execute()\n" +
                "    } catch (_: SocketTimeoutException) {\n" +
                "        return ServiceResult.Error(DataError.Remote.REQUEST_TIME_OUT)\n" +
                "    } catch (_: UnresolvedAddressException) {\n" +
                "        return ServiceResult.Error(DataError.Remote.NO_INTERNET)\n" +
                "    } catch (_: Exception) {\n" +
                "        currentCoroutineContext().ensureActive()\n" +
                "        return ServiceResult.Error(DataError.Remote.UNKNOWN)\n" +
                "    }\n" +
                "    return responseToResult(response)\n" +
                "}\n" +
                "\n" +
                "suspend inline fun <reified T> responseToResult(\n" +
                "    response: HttpResponse\n" +
                "): ServiceResult<T, DataError.Remote> {\n" +
                "    return when(response.status.value) {\n" +
                "        in 200..299 -> {\n" +
                "            try {\n" +
                "                ServiceResult.Success(response.body<T>())\n" +
                "            } catch (_: NoTransformationFoundException) {\n" +
                "                ServiceResult.Error(DataError.Remote.SERIALIZATION)\n" +
                "            }\n" +
                "        }\n" +
                "        408 -> ServiceResult.Error(DataError.Remote.REQUEST_TIME_OUT)\n" +
                "        429 -> ServiceResult.Error(DataError.Remote.TOO_MANY_REQUESTS)\n" +
                "        in 500..599 -> ServiceResult.Error(DataError.Remote.SERVER_ERROR)\n" +
                "        else -> ServiceResult.Error(DataError.Remote.UNKNOWN)\n" +
                "    }\n" +
                "}"
    }
}
