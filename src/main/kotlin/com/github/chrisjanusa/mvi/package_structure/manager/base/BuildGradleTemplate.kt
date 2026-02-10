package com.github.chrisjanusa.mvi.package_structure.manager.base

interface BuildGradleTemplate {
    fun createContent(namespace: String, pluginPrefix: String): String
}
