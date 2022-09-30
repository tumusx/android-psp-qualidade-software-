package com.github.tumusx.qualidadedesoftware_alugacaodequadras

import android.app.Application
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AreaApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(this@AreaApplication)
            modules(koinModule)
        }
        super.onCreate()
    }
}