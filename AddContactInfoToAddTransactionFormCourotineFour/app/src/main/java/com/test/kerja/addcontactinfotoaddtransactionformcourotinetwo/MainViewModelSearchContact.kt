package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModelSearchContact(
    context: Application,
    private val myContactsRepository: MyContactsRepository
) : AndroidViewModel(context) {


    var userssearch: MutableLiveData<List<MyContact>> = MutableLiveData()

    fun getUsers(nameSearch:String) {
        viewModelScope.launch {
            var result: List<MyContact>? = null
            withContext(Dispatchers.IO) {
                result = myContactsRepository.fetchContactSearch(nameSearch)
            }
            userssearch.value = result!!
        }
    }

    var myContacts: LiveData<List<MyContact>> = liveData {
        emit(myContactsRepository.fetchContacts())
    }



}