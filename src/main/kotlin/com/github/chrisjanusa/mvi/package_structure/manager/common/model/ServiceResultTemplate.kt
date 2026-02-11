package com.github.chrisjanusa.mvi.package_structure.manager.common.model

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class ServiceResultTemplate(
    packageManager: Manager
) : Template(packageManager, ServiceResultFileManager.NAME) {
    override fun createContent(): String {
        return "sealed interface ServiceResult<out D, out E: Error> {\n" +
                "    data class Success<out D>(val data: D): ServiceResult<D, Nothing>\n" +
                "    data class Error<out E: Error>(val error: E):\n" +
                "        ServiceResult<Nothing, E>\n" +
                "}\n" +
                "\n" +
                "inline fun <T, E: Error, R> ServiceResult<T, E>.map(map: (T) -> R): ServiceResult<R, E> {\n" +
                "    return when(this) {\n" +
                "        is ServiceResult.Error -> ServiceResult.Error(error)\n" +
                "        is ServiceResult.Success -> ServiceResult.Success(map(data))\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "inline fun <T, E: Error> ServiceResult<T, E>.onSuccess(action: (T) -> Unit): ServiceResult<T, E> {\n" +
                "    return when(this) {\n" +
                "        is ServiceResult.Error -> this\n" +
                "        is ServiceResult.Success -> {\n" +
                "            action(data)\n" +
                "            this\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "inline fun <T, E: Error> ServiceResult<T, E>.onError(action: (E) -> Unit): ServiceResult<T, E> {\n" +
                "    return when(this) {\n" +
                "        is ServiceResult.Error -> {\n" +
                "            action(error)\n" +
                "            this\n" +
                "        }\n" +
                "        is ServiceResult.Success -> this\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "fun <T, E: Error> ServiceResult<T, E>.toEmptyResult(): EmptyResult<E> {\n" +
                "    return this.map {  }\n" +
                "}\n" +
                "\n" +
                "typealias EmptyResult<E> = ServiceResult<Unit, E>"
    }
}
