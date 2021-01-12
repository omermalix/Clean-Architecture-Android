package com.elifox.legocatalog.presentation.base
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel(), LifecycleObserver {

  override fun onCleared() {
    super.onCleared()
  }
}
