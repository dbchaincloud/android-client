package com.gcigb.dbchain

import android.content.Context
import com.gcigb.dbchain.util.AppFilePath
import com.gcigb.network.NetworkLib
import okhttp3.Interceptor
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo


/**
 * @author: Xiao Bo
 * @date: 13/10/2020
 */
class DBChain {

    companion object {
        lateinit var appCode: String
            private set
        internal lateinit var baseUrl: String
        internal lateinit var chainId: String
        lateinit var dbChainKey: DbChainKey
        lateinit var dbChainEncrypt: IDBChainEncrypt

        fun init(
            context: Context, appCode: String, baseUrl: String, chainId: String,
            dbChainEncrypt: IDBChainEncrypt,
            isDebug: Boolean = false,
            testLogTag: String = "tag_test",
            errorLogTag: String = "tag_error",
            httpLogTag: String = "tag_http",
            interceptors: List<Interceptor>? = null
        ) {
            this.appCode = appCode
            this.baseUrl = baseUrl
            this.chainId = chainId
            this.dbChainEncrypt = dbChainEncrypt
            NetworkLib.initNetworkModule(context, isDebug, testLogTag, errorLogTag, httpLogTag, baseUrl, interceptors)
            AppFilePath.init(context)
        }

        fun withDBChainKey(dbChainKey: DbChainKey) {
            this.dbChainKey = dbChainKey
        }

        fun withAppCode(appCode: String){
            this.appCode = appCode
        }
    }
}