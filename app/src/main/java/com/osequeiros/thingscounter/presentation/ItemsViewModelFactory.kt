package com.osequeiros.thingscounter.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osequeiros.thingscounter.di.DependenciesProvider

class ItemsViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ItemsViewModel::class.java) {
            return DependenciesProvider(context)
                .instanceItemsViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown model class :$modelClass")
        }
    }

}