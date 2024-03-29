package com.gcigb.network.util

import android.util.Log
import com.gcigb.network.NetworkLib
import com.gcigb.network.NetworkLib.Companion.DEBUG
import com.gcigb.network.NetworkLib.Companion.TAG_ERROR
import com.gcigb.network.NetworkLib.Companion.TAG_TEST
import com.google.gson.Gson

/**
 * @author: Xiao Bo
 * @date: 18/7/2020
 */


fun logHttp(msg: String) {
    if (DEBUG) {
        Log.i(NetworkLib.TAG_HTTP, msg)
    }
}

fun logV(tag: String, msg: String) {
    if (DEBUG) {
        Log.v(tag, msg)
    }
}

fun logD(tag: String, msg: String) {
    if (DEBUG) {
        Log.d(tag, msg)
    }
}

fun logI(msg: String) {
    logI(TAG_TEST, msg)
}

fun logI(any: Any) {
    logI(TAG_TEST, Gson().toJson(any))
}

fun logI(tag: String, msg: String) {
    if (DEBUG) {
        Log.i(tag, msg)
    }
}

fun logW(tag: String, msg: String) {
    if (DEBUG) {
        Log.w(tag, msg)
    }
}

fun logE(msg: String) {
    logE(TAG_ERROR, msg)
}

fun logE(tag: String, msg: String) {
    if (DEBUG) {
        Log.e(tag, msg)
    }
}