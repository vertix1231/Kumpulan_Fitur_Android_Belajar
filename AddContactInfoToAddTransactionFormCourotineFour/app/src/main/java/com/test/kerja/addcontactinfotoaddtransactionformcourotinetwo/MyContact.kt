package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyContact(
    var name :String,
    var number: String,
//    var id:Int,
) : Parcelable
