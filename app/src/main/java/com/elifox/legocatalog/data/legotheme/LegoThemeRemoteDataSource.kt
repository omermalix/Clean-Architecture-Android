package com.elifox.legocatalog.data.legotheme

import com.elifox.legocatalog.domain.BaseDataSource
import com.elifox.legocatalog.domain.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoThemeRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }

}
