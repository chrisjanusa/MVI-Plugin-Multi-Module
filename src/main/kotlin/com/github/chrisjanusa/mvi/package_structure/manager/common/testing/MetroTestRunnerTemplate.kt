package com.github.chrisjanusa.mvi.package_structure.manager.common.testing

import com.github.chrisjanusa.mvi.package_structure.Manager
import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class MetroTestRunnerTemplate(
    packageManager: Manager
) : Template(packageManager, MetroTestRunnerFileManager.NAME) {
    override fun createContent(): String {
        return "import android.app.Application\n" +
                "import android.content.Context\n" +
                "import androidx.test.runner.AndroidJUnitRunner\n" +
                "\n" +
                "class MetroTestRunner : AndroidJUnitRunner() {\n" +
                "    override fun newApplication(\n" +
                "        cl: ClassLoader?,\n" +
                "        className: String?,\n" +
                "        context: Context?\n" +
                "    ): Application {\n" +
                "        return super.newApplication(cl, TestApplication::class.java.name, context)\n" +
                "    }\n" +
                "}"
    }
}
