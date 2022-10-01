package com.github.tumusx.qualidadedesoftware_alugacaodequadras.di

import android.app.Application
import androidx.room.Room
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.dao.AreaDAO
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.datasource.AreaDatasource
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository.AreaRepositoryImpl
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.AreaViewModel
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.CadastroQuadraViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    fun provideDatabaseManager(application: Application): AreaDatasource {
        return Room.databaseBuilder(application, AreaDatasource::class.java, "area.db").fallbackToDestructiveMigration() .build()
    }

    fun provideDao(database: AreaDatasource): AreaDAO = database.areaDAO()

    factory { provideDao(get()) }
    factory { provideDatabaseManager(androidApplication()) }

    factory {
        AreaRepositoryImpl(get())
    }
    viewModel {
        AreaViewModel(get())
    }
    viewModel {
        CadastroQuadraViewModel(get())
    }

}