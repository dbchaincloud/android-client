package com.gcigb.dbchain

import com.gcigb.dbchain.bean.result.DBChainQueryResult
import com.gcigb.dbchain.cosmossig.sign
import com.gcigb.dbchain.net.ApiService
import com.gcigb.dbchain.util.coding.base58Encode
import com.gcigb.network.RetrofitClient

fun createAccessToken(): String {
    val currentTimeMillis = "${System.currentTimeMillis()}"
    val signObj = sign(currentTimeMillis.toByteArray(), DBChain.dbChainKey.privateKeyBytes)
    val encodedPubKey = base58Encode(DBChain.dbChainKey.publicKeyBytes)
    val encodedSig = base58Encode(signObj)
    return "${encodedPubKey}:${currentTimeMillis}:${encodedSig}"
}

/**
 * 打一个积分
 */
suspend fun requestAppUser(): DBChainQueryResult {
    val result = RetrofitClient.sendRequestForReturn {
        return@sendRequestForReturn RetrofitClient.createService(DBChain.baseUrl, ApiService::class.java)
            .requestAppUser(createAccessToken())
            .await()
    }
    return DBChainQueryResult(result?.isSuccessful ?: false, null)
}

suspend fun getToken(address: String): Int {
    return RetrofitClient.sendRequestForReturn {
        val result = RetrofitClient.createService(DBChain.baseUrl, ApiService::class.java)
            .getToken(address)
            .await()

        val coins = result.result.value.coins
        return run breaking@{
            coins.forEach {
                if (it.denom == "dbctoken") return@breaking it.amount.toInt()
            }
            return@breaking 0
        }
    } ?: return 0
}

