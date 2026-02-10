package com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.helper
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.generated.SharedGeneratedPackage
import com.github.chrisjanusa.mvi.package_structure.manager.old.feature.shared.SharedPackage
import com.github.chrisjanusa.mvi.package_structure.parent_provider.FeatureChild

interface ISharedFileManager: FeatureChild {
    val generatedPackage: SharedGeneratedPackage?
    val sharedPackage: SharedPackage
    val featureName: String
}