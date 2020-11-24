package com.example.placeholder.home

import com.example.placeholder.common.InjectionModule
import com.example.placeholder.home.api.HomeApi
import com.example.placeholder.home.interactor.HomeInteractor
import com.example.placeholder.home.repository.HomeRepository
import com.example.placeholder.home.repository.HomeRepositoryImpl
import com.example.placeholder.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule : InjectionModule {

    override fun create() = module {
        viewModel { HomeViewModel(get()) }
        factory { HomeInteractor(get()) }
        single {
            val homeApi = get<Retrofit>().create(HomeApi::class.java)
            HomeRepositoryImpl(homeApi)
        } bind HomeRepository::class
    }
}