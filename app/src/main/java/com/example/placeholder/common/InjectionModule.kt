package com.example.placeholder.common

import org.koin.core.module.Module

interface InjectionModule {
    fun create(): Module
}