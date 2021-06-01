package com.gcigb.network

import android.content.Context
import com.gcigb.network.interceptor.LogInterceptor
import okhttp3.Interceptor

/**
 * @author: Xiao Bo
 * @date: 18/7/2020
 */


class NetworkLib {
    companion object {
        internal lateinit var mContext: Context
        internal var DEBUG = false
        internal var interceptorList: MutableList<Interceptor>? = null
        internal lateinit var TAG_TEST: String
        internal lateinit var TAG_ERROR: String
        internal lateinit var TAG_HTTP: String
        internal lateinit var BASE_URL: String

        fun initNetworkModule(
            context: Context,
            isDebug: Boolean,
            testLogTag: String = "tag_test",
            errorLogTag: String = "tag_error",
            httpLogTag: String = "tag_http",
            baseUrl: String = "",
            interceptors: List<Interceptor>? = null
        ) {
            mContext = context
            DEBUG = isDebug
            TAG_TEST = testLogTag
            TAG_ERROR = errorLogTag
            BASE_URL = baseUrl
            TAG_HTTP = httpLogTag.also { LogInterceptor.TAG = it }
            interceptors?.let { list ->
                if (list.isEmpty()) return@let
                interceptorList = mutableListOf<Interceptor>().apply {
                    addAll(list)
                }
            }
        }
    }
}

