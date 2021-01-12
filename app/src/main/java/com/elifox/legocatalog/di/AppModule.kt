package com.elifox.legocatalog.di

import android.content.Context
import android.content.SharedPreferences
import com.elifox.legocatalog.BuildConfig
import com.elifox.legocatalog.R
import com.elifox.legocatalog.domain.AuthInterceptor
import com.elifox.legocatalog.domain.LegoService
import com.elifox.legocatalog.data.legotheme.LegoThemeRemoteDataSource
import com.elifox.legocatalog.util.Constants
import com.elifox.legocatalog.util.VerticalItemDecoration
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
                Constants.PreferenceKeys,
                Context.MODE_PRIVATE
        )
    }

    @Provides
    fun providePrivateOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @Singleton
    @Provides
    fun provideLegoService(okhttpClient: OkHttpClient, converterFactory: GsonConverterFactory)
            = provideService(okhttpClient, converterFactory, LegoService::class.java)

    @Singleton
    @Provides
    fun provideLegoThemeRemoteDataSource(legoService: LegoService)
            = LegoThemeRemoteDataSource(legoService)

    @Singleton
    @Provides
    fun provideGson()
            = Gson()

    @Singleton
    @Provides
    fun provideGsonConvertore(gson: Gson) : GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(okhttpClient: OkHttpClient, converterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(LegoService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient, converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
