package org.override.book.logicave

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class LogicaveViewModel : ViewModel() {

    private val _state = MutableStateFlow(LogicaveState())
    val state = _state
        .onStart { }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LogicaveState()
        )

    fun onAction(action: LogicaveAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}