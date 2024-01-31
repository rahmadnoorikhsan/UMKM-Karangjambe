package com.karangjambe.umkm.utils

import java.text.NumberFormat
import java.util.Locale

object Converter {

    fun Int.toCurrencyFormat(): String {
        val localeID = Locale("in", "ID")
        val doubleValue = this.toDouble()
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(doubleValue)
    }
}