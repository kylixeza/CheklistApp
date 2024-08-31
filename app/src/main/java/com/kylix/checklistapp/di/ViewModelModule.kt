package com.kylix.checklistapp.di

import com.kylix.checklistapp.screens.add_checklist.AddChecklistViewModel
import com.kylix.checklistapp.screens.auth.login.LoginViewModel
import com.kylix.checklistapp.screens.auth.register.RegisterViewModel
import com.kylix.checklistapp.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddChecklistViewModel(get()) }
}