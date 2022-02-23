package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.content.ContentResolver
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import kotlin.coroutines.coroutineContext

class MyContactsDataSource(private val contentResolver: ContentResolver) {

    fun fetchContacts(): List<MyContact> {
        val result: MutableList<MyContact> = mutableListOf()
        val resulthashmap = HashMap<String,MyContact>()
        val cols = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,
            null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                resulthashmap.put(cursor.getString(0),
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1)
                    ))

                cursor.moveToNext()

            }
            var sortHasmapContact = resulthashmap.toSortedMap()
            for (datakeycontactmap in sortHasmapContact.keys){
                println("data tambah hasmap contact dengan put adalah \n: ${sortHasmapContact[datakeycontactmap]} \n dengan key $datakeycontactmap \n --------------------------------------------------------------------------------")
                result.add(sortHasmapContact[datakeycontactmap]!!)
            }
            println("data hashmap contact yang sudah dibershikan menjadi \n: ${sortHasmapContact.toSortedMap()}")
            cursor.close()
        }
        return result.toList()
    }

    fun fetchContactsSearch(cari:String): List<MyContact> {
        val result: MutableList<MyContact> = mutableListOf()
        val resulthashmap = HashMap<String,MyContact>()
        var cols = listOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE ?",Array(1){"%$cari%"},ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                resulthashmap.put(cursor.getString(0),
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1)
                    ))
//                result.add(
//                    MyContact(
//                        cursor.getString(0),
//                        cursor.getString(1)
//                    )
//                ) //add the item
//                Log.d("tag","id nya item terpilih adalah ${cursor?.getLong(0)} dengan nama ${cursor.getString(1)}")
                cursor.moveToNext()
            }
            var sortHasmapContact = resulthashmap.toSortedMap()
            for (datakeycontactmap in sortHasmapContact.keys){
                println("search data tambah hasmap contact dengan put adalah : ${sortHasmapContact[datakeycontactmap]}  dengan key $datakeycontactmap \n --------------------------------------------------------------------------------")
                result.add(sortHasmapContact[datakeycontactmap]!!)

            }
            println(" searchdata hashmap contact yang sudah dibershikan menjadi \n: ${sortHasmapContact.toSortedMap()}")
            cursor.close()
        }
        return result.toList()
    }
//    fun String.toContactImageUri() = Uri.withAppendedPath(
//        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, this.toLong()),
//        ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
//    ).toString()

}

//fun fetchContacts(): List<MyContact> {
//    val result: MutableList<MyContact> = mutableListOf()
//    val resulthashmap = HashMap<String,MyContact>()
//    val cols = listOf<String>(
//        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//        ContactsContract.CommonDataKinds.Phone.NUMBER,
//        ContactsContract.CommonDataKinds.Phone._ID
//    ).toTypedArray()
//    val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,
//        null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
//    cursor?.let {
//        cursor.moveToFirst()
//        while (!cursor.isAfterLast) {
//            resulthashmap.put(cursor.getString(0),
//                MyContact(
//                    cursor.getString(0),
//                    cursor.getString(1)
//                ))
//            cursor.moveToNext()
//        }
//        val sortHasmapContact = resulthashmap.toSortedMap()
//        for (datakeycontactmap in sortHasmapContact.keys){
//            result.add(sortHasmapContact[datakeycontactmap]!!)
//        }
//        cursor.close()
//    }
//    return result.toList()
//}
//
//fun fetchContactsSearch(cari:String): List<MyContact> {
//    val result: MutableList<MyContact> = mutableListOf()
//    val resulthashmap = HashMap<String,MyContact>()
//    var cols = listOf(
//        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//        ContactsContract.CommonDataKinds.Phone.NUMBER,
//        ContactsContract.CommonDataKinds.Phone._ID
//    ).toTypedArray()
//    val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,
//        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE ?",Array(1){"%$cari%"},ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
//    cursor?.let {
//        cursor.moveToFirst()
//        while (!cursor.isAfterLast) {
//            resulthashmap.put(cursor.getString(0),
//                MyContact(
//                    cursor.getString(0),
//                    cursor.getString(1)
//                ))
//            cursor.moveToNext()
//        }
//        val sortHasmapContact = resulthashmap.toSortedMap()
//        for (datakeycontactmap in sortHasmapContact.keys){
//            result.add(sortHasmapContact[datakeycontactmap]!!)
//        }
//        cursor.close()
//    }
//    return result.toList()
//}
//


