package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers

class MyViewModelSearchContactFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModelSearchContact::class.java)) {
            val source =
                MyContactsDataSource(application.contentResolver)
            MainViewModelSearchContact(application, MyContactsRepository(source, Dispatchers.IO)) as T
        } else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}