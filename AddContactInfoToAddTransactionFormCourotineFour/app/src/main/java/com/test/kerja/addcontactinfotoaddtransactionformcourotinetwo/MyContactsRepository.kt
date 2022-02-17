package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyContactsRepository(private val source: MyContactsDataSource, private val myDispatcher: CoroutineDispatcher) {

    suspend fun fetchContacts(): List<MyContact> {
        return withContext(myDispatcher) {
            delay(8000)
            source.fetchContacts()
        }
    }
    suspend fun fetchContactSearch(cari:String):List<MyContact>{
        return withContext(myDispatcher) {
            source.fetchContactsSearch(cari)
        }
    }

//
}