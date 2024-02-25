package com.soopeach.reddy

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ReddyApplication: Application() {
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@ReddyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule,
                    localDataStoreModule,
                    useCaseModule,
                    networkModule,
                ),
            )
        }

        super.onCreate()
    }
}