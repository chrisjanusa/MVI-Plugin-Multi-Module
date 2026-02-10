package com.github.chrisjanusa.mvi.package_structure.manager.foundation.foundation.viewmodel

import com.github.chrisjanusa.mvi.package_structure.manager.base.Template

class FoundationPluginViewModelTemplate(
    val foundationViewModelPackage: FoundationViewModelPackage,
    fileName: String
) : Template(
    packageManager = foundationViewModelPackage,
    fileName = fileName
) {

    override fun createContent(): String {
        val foundationPackage = foundationViewModelPackage.foundationPackage
        return  "import androidx.lifecycle.ViewModel\n" +
                "import androidx.lifecycle.viewModelScope\n" +
                "import ${foundationPackage.action?.packagePath}\n" +
                "import ${foundationPackage.actionEffect?.packagePath}\n" +
                "import ${foundationPackage.onAction?.packagePath}\n" +
                "import ${foundationPackage.stateEffect?.packagePath}\n" +
                "import ${foundationPackage.pluginSlice?.packagePath}\n" +
                "import ${foundationPackage.pluginState?.packagePath}\n" +
                "import kotlinx.coroutines.Dispatchers\n" +
                "import kotlinx.coroutines.flow.MutableSharedFlow\n" +
                "import kotlinx.coroutines.flow.MutableStateFlow\n" +
                "import kotlinx.coroutines.flow.asStateFlow\n" +
                "import kotlinx.coroutines.flow.update\n" +
                "import kotlinx.coroutines.launch\n" +
                "import kotlin.jvm.Volatile\n" +
                "\n" +
                "abstract class PluginViewModel<State : PluginState, Slice : PluginSlice>() : ViewModel() {\n" +
                "\n" +
                "    abstract val actionEffectSet: Set<ActionEffect>\n" +
                "    abstract val stateEffectSet: Set<StateEffect<State, Slice>>\n" +
                "\n" +
                "    abstract val initialState: State\n" +
                "    abstract val initialSlice: Slice\n" +
                "    private val _state by lazy { MutableStateFlow(initialState) }\n" +
                "    val state by lazy { _state.asStateFlow() }\n" +
                "\n" +
                "    private val slice by lazy { MutableStateFlow(initialSlice) }\n" +
                "\n" +
                "    private val actionFlow = MutableSharedFlow<Action>()\n" +
                "    \n" +
                "    @Volatile\n" +
                "    var childActionListener: OnAction = {}\n" +
                "\n" +
                "\n" +
                "    open fun onAction(action: Action) {\n" +
                "        viewModelScope.launch {\n" +
                "            childActionListener(action)\n" +
                "            actionFlow.emit(action)\n" +
                "        }\n" +
                "\n" +
                "        // Update is thread safe using compareAndSet\n" +
                "        _state.update {\n" +
                "            reduce(action, state.value, slice.value)\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    fun attachChildActionListener(newChildActionListener: OnAction) {\n" +
                "        childActionListener = newChildActionListener\n" +
                "    }\n" +
                "\n" +
                "    abstract fun reduce(action: Action, state: State, slice: Slice): State\n" +
                "\n" +
                "    fun onSliceUpdate(newSlice: Slice) {\n" +
                "        slice.update {\n" +
                "            newSlice\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    protected fun triggerEffects() {\n" +
                "        viewModelScope.launch(Dispatchers.Default) {\n" +
                "            actionEffectSet.forEach { effect ->\n" +
                "                launch {\n" +
                "                    effect.launchEffect(\n" +
                "                        actionFlow = actionFlow,\n" +
                "                        onAction = {\n" +
                "                            onAction(it)\n" +
                "                        }\n" +
                "                    )\n" +
                "                }\n" +
                "            }\n" +
                "            stateEffectSet.forEach { effect ->\n" +
                "                launch {\n" +
                "                    effect.launchEffect(\n" +
                "                        actionFlow = actionFlow,\n" +
                "                        stateFlow = state,\n" +
                "                        sliceFlow = slice,\n" +
                "                        onAction = {\n" +
                "                            onAction(it)\n" +
                "                        }\n" +
                "                    )\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }
}
