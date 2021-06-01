package com.dbchain.demo

import android.app.Application
import com.gcigb.dbchain.DBChain

/**
 * @author: Xiao Bo
 * @date: 1/6/2021
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appCode = "Your AppCode"
        val baseUrl = "Your BaseUrl"
        val chainId = "Your ChainId"
        val debug = BuildConfig.DEBUG
        DBChain.init(this, appCode, baseUrl, chainId, debug)
    }
}