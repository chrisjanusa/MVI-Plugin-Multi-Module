package com.github.chrisjanusa.mvi.package_structure

import com.github.chrisjanusa.mvi.helper.file_helper.isValidFile
import com.github.chrisjanusa.mvi.package_structure.instance_companion.InstanceCompanion
import com.github.chrisjanusa.mvi.package_structure.manager.project.ProjectPackage
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.vfs.VirtualFile

fun AnActionEvent.getManager(): Manager? {
    val currFile = getData(PlatformDataKeys.VIRTUAL_FILE) ?: return null
    return currFile.getManager()
}

// TODO: This is super expensive so double check if we can remove this somehow
fun VirtualFile.getManager() : Manager? {
    if (ProjectPackage.isInstance(this)) return ProjectPackage(this)
    val potentialManagers = parent.getChildrenInstanceCompanion()
    if (potentialManagers.isEmpty()) return null
    val managerInstanceCompanion  = potentialManagers.firstOrNull {
        it.isInstance(this)
    } ?: return null
    return managerInstanceCompanion.createInstance(this)
}

private fun VirtualFile.getChildrenInstanceCompanion(): List<InstanceCompanion> {
    if (ProjectPackage.isInstance(this)) return ProjectPackage.allChildrenInstanceCompanions
    val potentialManagers = parent.getChildrenInstanceCompanion()
    val managerInstanceCompanion  = potentialManagers.firstOrNull {
        it.isInstance(this)
    } ?: return emptyList()
    return managerInstanceCompanion.allChildrenInstanceCompanions
}

fun AnActionEvent.getManagerOfType(managerType: InstanceCompanion): Manager? {
    val currFile = getData(PlatformDataKeys.VIRTUAL_FILE) ?: return null
    return currFile.getManagerOfType(managerType)
}

fun VirtualFile.getManagerOfType(managerType: InstanceCompanion): Manager? {
    if (managerType.isInstance(this)) return managerType.createInstance(this)
    if (isValidFile())
        return parent.getManagerOfType(managerType)
    else
        return null
}

