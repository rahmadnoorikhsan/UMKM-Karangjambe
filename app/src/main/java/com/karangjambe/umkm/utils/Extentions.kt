package com.karangjambe.umkm.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import java.net.URLEncoder

fun openWhatsAppChat(context: Context, toNumber: String, message: String) {
    val url = "https://api.whatsapp.com/send?phone=$toNumber&text=" + URLEncoder.encode(
        message,
        "UTF-8"
    )
    try {
        context.packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
        context.startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
    } catch (e: PackageManager.NameNotFoundException) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}