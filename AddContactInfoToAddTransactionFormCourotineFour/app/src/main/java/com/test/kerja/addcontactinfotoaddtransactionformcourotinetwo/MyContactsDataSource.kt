package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.content.ContentResolver
import android.provider.ContactsContract

class MyContactsDataSource(private val contentResolver: ContentResolver) {

    fun fetchContacts(): List<MyContact> {
        val result: MutableList<MyContact> = mutableListOf()
        var cols = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,
            null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                result.add(
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1)
                    )
                ) //add the item

                cursor.moveToNext()
            }
            cursor.close()
        }
        return result.toList()
        println("----------------------------------------------------------------------------------- dilakukan viewmodel kotak data  degan semua kontak adalah $result")

    }
    fun fetchContactsSearch(cari:String): List<MyContact> {
        val result: MutableList<MyContact> = mutableListOf()
        var cols = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE ?",Array(1){"%$cari%"},ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                result.add(
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1)
                    )
                ) //add the item
                cursor.moveToNext()
            }
            cursor.close()
        }
        println("----------------------------------------------------------------------------------- dilakukan viewmodel search kotak data semua dengan yang di search adalah $result")

        return result.toList()
    }
//    fun String.toContactImageUri() = Uri.withAppendedPath(
//        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, this.toLong()),
//        ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
//    ).toString()

}

//    private var myContact: MyContact? =null
////    private lateinit var myContact: MyContact
//    private var hashMapContact: HashMap<Long, MyContact>? = null


//                val id = cursor.getLong(0)
//                myContact = hashMapContact?.get(id)
//                if (myContact==null){
//                    result.add(
//                        MyContact(
//                            cursor.getString(0),
//                            cursor.getString(1)
//                        )
//                    ) //add the item
//                }


