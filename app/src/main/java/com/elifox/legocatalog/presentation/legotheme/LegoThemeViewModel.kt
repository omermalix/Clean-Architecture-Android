

package com.elifox.legocatalog.presentation.legotheme

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.elifox.legocatalog.data.legotheme.LegoThemeRepository
import com.elifox.legocatalog.presentation.base.BaseViewModel

/**
 * The ViewModel for [LegoThemeFragment].
 */
class LegoThemeViewModel @ViewModelInject constructor(repository: LegoThemeRepository) : BaseViewModel() {

    val legoThemes= repository.themes
}
