package com.elifox.legocatalog.data.legotheme

import com.elifox.legocatalog.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class LegoThemeRepository @Inject constructor(private val remoteSource: LegoThemeRemoteDataSource) {

    val themes = resultLiveData(networkCall = { remoteSource.fetchData() })

}
