package com.gcigb.dbchain

import android.content.Context
import com.gcigb.dbchain.util.AppFilePath
import com.gcigb.network.NetworkLib
import okhttp3.Interceptor
import org.spongycastle.jce.provider.BouncyCastleProvider
import java.security.Security


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

        fun init(
            context: Context, appCode: String, baseUrl: String, chainId: String,
            isDebug: Boolean = false,
            testLogTag: String = "tag_test",
            errorLogTag: String = "tag_error",
            httpLogTag: String = "tag_http",
            interceptors: List<Interceptor>? = null
        ) {
            this.appCode = appCode
            this.baseUrl = baseUrl
            this.chainId = chainId
            NetworkLib.initNetworkModule(context, isDebug, testLogTag, errorLogTag, httpLogTag, baseUrl, interceptors)
            // Android里的 secp256k1 被阉割了，需要添加这行代码
            Security.insertProviderAt(BouncyCastleProvider(), 1)
            AppFilePath.init(context)
        }

        fun withDBChainKey(dbChainKey: DbChainKey) {
            this.dbChainKey = dbChainKey
        }
    }
}