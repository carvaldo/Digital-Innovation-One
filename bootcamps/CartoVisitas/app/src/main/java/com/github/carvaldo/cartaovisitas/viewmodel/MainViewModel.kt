package com.github.carvaldo.cartaovisitas.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.carvaldo.cartaovisitas.ui.AddFragment
import com.github.carvaldo.cartaovisitas.ui.ListFragment

class MainViewModel {
    companion object {
        @JvmStatic fun getViewModelFrom(viewModelStoreOwner: ViewModelStoreOwner, fragment: Fragment) =
            when (fragment) {
                is AddFragment -> ViewModelProvider(viewModelStoreOwner)[CartaoViewModel::class.java]
                is ListFragment -> ViewModelProvider(viewModelStoreOwner)[CartaoViewModel::class.java]
                else -> throw RuntimeException("Activity ou Fragment não mapeado.")
            }
    }
}