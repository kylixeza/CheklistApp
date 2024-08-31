package com.kylix.checklistapp.di

import com.kylix.checklistapp.screens.checklist.add_checklist.AddChecklistViewModel
import com.kylix.checklistapp.screens.checklist_item.add_checklist_item.AddChecklistItemViewModel
import com.kylix.checklistapp.screens.auth.login.LoginViewModel
import com.kylix.checklistapp.screens.auth.register.RegisterViewModel
import com.kylix.checklistapp.screens.checklist.ChecklistViewModel
import com.kylix.checklistapp.screens.checklist_item.ChecklistItemViewModel
import com.kylix.checklistapp.screens.checklist_item.detail.DetailChecklistItemViewModel
import com.kylix.checklistapp.screens.checklist_item.edit.EditChecklistItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ChecklistViewModel(get()) }
    viewModel { AddChecklistViewModel(get()) }
    viewModel { ChecklistItemViewModel(get()) }
    viewModel { AddChecklistItemViewModel(get()) }
    viewModel { DetailChecklistItemViewModel(get()) }
    viewModel { EditChecklistItemViewModel(get()) }
}