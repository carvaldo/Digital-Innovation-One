package com.github.carvaldo.cartaovisitas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.github.carvaldo.cartaovisitas.exception.TechnicalException

abstract class BaseFragment: Fragment() {
    private var onViewCreatedCalled = false
    private var onCreateCalled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(CustomLifeCyclerObserver())
        onCreateCalled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observar()
        processarArgumentos()
        registrarListeners()
        onViewCreatedCalled = true
    }

    protected abstract fun registrarListeners()

    protected abstract fun observar()

    protected abstract fun processarArgumentos()

    private inner class CustomLifeCyclerObserver: LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onCreate() {
            if (!onViewCreatedCalled) {
                throw TechnicalException("super.onViewCreated(view: View, " +
                        "savedInstanceState: Bundle?) não foi executado. " +
                        "Necessário adicionar a chamada à super classe.")
            }
            if (!onCreateCalled) {
                throw TechnicalException("super.onCreate(savedInstanceState: Bundle?)" +
                        " não foi executado. Necessário adicionar a chamada à super classe.")
            }
        }
    }
}