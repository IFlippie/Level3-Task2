package com.iflippie.level3_task2

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Websites (
    var theLink: Uri
): Parcelable{
    companion object  {
        val ALL_LINKS = arrayOf<Uri>(
            Uri.parse("https://google.nl")
        )
    }
}