package kz.noxiq.core.base

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kz.noxiq.core.adapter.IKDispatcher

abstract class BaseViewModel(application: Application) : AndroidViewModel(application), IKDispatcher {

    val context: Context
        get() = getApplication()

    val res: Resources = context.resources

    private val _redirectEventFragment = SingleLiveEvent<EventWrapper<Pair<Int, Bundle?>>>()
    private val redirectEventFragment: LiveData<EventWrapper<Pair<Int, Bundle?>>>
        get() = _redirectEventFragment

    val redirectFragment: LiveData<EventWrapper<Pair<Int, Bundle?>>> =
        MediatorLiveData<EventWrapper<Pair<Int, Bundle?>>>().apply {
            addSource(redirectEventFragment) { valueLiveDataViewModel ->
                value = valueLiveDataViewModel
            }
        }
}