package com.dbchain.demo

import android.app.Application
import com.gcigb.dbchain.DBChain
import com.gcigb.dbchain.IDBChainEncrypt
import org.bitcoinj.crypto.DeterministicKey

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
        DBChain.init(
            context = this,
            appCode = appCode,
            baseUrl = baseUrl,
            chainId = chainId,
            isDebug = debug,
            dbChainEncrypt = object : IDBChainEncrypt{
                override fun sign(privateByteArray: ByteArray, data: ByteArray): ByteArray {
                    return byteArrayOf()
                }

                override fun verify(
                    publicKeyByteArray: ByteArray,
                    data: ByteArray,
                    sign: ByteArray
                ): Boolean {
                    return false
                }

                override fun encrypt(publicKeyByteArray: ByteArray, data: ByteArray): ByteArray {
                    return byteArrayOf()
                }

                override fun decrypt(privateByteArray: ByteArray, data: ByteArray): ByteArray {
                    return byteArrayOf()
                }

                override fun generateAddressByPublicKeyByteArray33(publicKeyByteArray33: ByteArray): String {
                    return ""
                }

                override fun generatePublicKey33ByPrivateKey(
                    privateByteArray: ByteArray,
                    dkKey: DeterministicKey
                ): ByteArray {
                    return byteArrayOf()
                }

                override fun generatePublicKey64ByPrivateKey(
                    privateByteArray: ByteArray,
                    dkKey: DeterministicKey
                ): ByteArray {
                    return byteArrayOf()
                }

            }
        )
    }
}