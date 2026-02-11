package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class TestApplicationTemplate(
    packageManager: Manager
) : Template(packageManager, TestApplicationFileManager.NAME) {
    override fun createContent(): String {
        return "import android.app.Activity\n" +
                "import android.app.Application\n" +
                "import android.app.Service\n" +
                "import android.content.BroadcastReceiver\n" +
                "import android.content.ContentProvider\n" +
                "import dev.zacsweers.metrox.android.MetroAppComponentProviders\n" +
                "import dev.zacsweers.metrox.android.MetroApplication\n" +
                "import dev.zacsweers.metro.Provider\n" +
                "import kotlin.reflect.KClass\n" +
                "\n" +
                "class TestApplication : Application(), MetroApplication {\n" +
                "    override val appComponentProviders: MetroAppComponentProviders\n" +
                "        get() = object : MetroAppComponentProviders {\n" +
                "            override val activityProviders: Map<KClass<out Activity>, Provider<Activity>> = emptyMap()\n" +
                "            override val providerProviders: Map<KClass<out ContentProvider>, Provider<ContentProvider>> = emptyMap()\n" +
                "            override val receiverProviders: Map<KClass<out BroadcastReceiver>, Provider<BroadcastReceiver>> = emptyMap()\n" +
                "            override val serviceProviders: Map<KClass<out Service>, Provider<Service>> = emptyMap()\n" +
                "        }\n" +
                "}"
    }
}
