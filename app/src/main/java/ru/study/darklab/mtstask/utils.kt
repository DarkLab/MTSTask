package ru.study.darklab.mtstask

import android.util.Log

fun printlog(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d("=====", message)
    }
}

val titles: List<String> by lazy {
    return@lazy List(100) {
        "Item # %03d".format(it + 1)
    }
}
