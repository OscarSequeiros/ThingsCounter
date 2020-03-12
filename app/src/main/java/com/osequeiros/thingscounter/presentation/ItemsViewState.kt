package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.mvibase.MviViewState
import com.osequeiros.thingscounter.presentation.model.UiItem

data class ItemsViewState(
    val isProcessing: Boolean,
    val items: List<UiItem>,
    val error: Throwable?
) : MviViewState {

    companion object {

        fun default(): ItemsViewState {
            return ItemsViewState(
                isProcessing = false,
                items = emptyList(),
                error = null
            )
        }
    }
}