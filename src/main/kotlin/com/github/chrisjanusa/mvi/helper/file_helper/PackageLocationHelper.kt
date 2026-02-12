//package com.github.chrisjanusa.mvi.helper.file_helper
//
//import com.github.chrisjanusa.mvi.package_structure.getManager
//import com.intellij.openapi.actionSystem.AnActionEvent
//import com.github.chrisjanusa.mvi.package_structure.manager.app.root.RootPackage
//import com.github.chrisjanusa.mvi.package_structure.parent_provider.RootDirectChild
//
//fun AnActionEvent.isRootPackageOrDirectChild(): Boolean {
//    val manager = getManager()
//    return manager is RootDirectChild || manager is RootPackage
//}
//
//fun AnActionEvent.isInsideServicePackage(): Boolean {
//    val manager = getManager()
//    return manager is ServiceChild || manager is FeatureServicePackage
//}
//
//fun AnActionEvent.isInsideFeaturePackage(): Boolean {
//    val manager = getManager()
//    return manager is FeatureChild || manager is FeaturePackage
//}
//
//fun AnActionEvent.isFeaturePackageOrDirectChild(): Boolean {
//    val manager = getManager()
//    return manager is FeatureDirectChild || manager is FeaturePackage
//}