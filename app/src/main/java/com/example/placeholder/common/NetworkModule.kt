package com.example.placeholder.common

import android.content.Context
import com.example.placeholder.BuildConfig
import com.example.placeholder.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object NetworkModule : InjectionModule {

    private const val DEFAULT_CONNECT_TIMEOUT_SECONDS = 60L
    private const val DEFAULT_READ_TIMEOUT_SECONDS = 60L

    override fun create() = module {
        single {
            GsonBuilder()
                .apply { if (BuildConfig.DEBUG) setPrettyPrinting() }
                .create()
        }

        single {
            OkHttpClient.Builder()
                .readTimeout(DEFAULT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .apply { if (BuildConfig.DEBUG) addInterceptor(createLoggingInterceptor(get())) }
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(get<Context>().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
        }
    }

    private fun createLoggingInterceptor(gson: Gson): HttpLoggingInterceptor {
        val okHttpLogTag = "OkHttp"

        val okHttpLogger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (!message.startsWith('{') && !message.startsWith('[')) {
                    Timber.tag(okHttpLogTag).d(message)
                    return
                }

                try {
                    val json = JsonParser().parse(message)
                    Timber.tag(okHttpLogTag).d(gson.toJson(json))
                } catch (e: Throwable) {
                    Timber.tag(okHttpLogTag).d(message)
                }
            }
        }
        return HttpLoggingInterceptor(okHttpLogger).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}