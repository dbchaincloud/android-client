package com.gcigb.dbchain

import com.gcigb.dbchain.util.Wallet
import com.gcigb.network.NetworkLib
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

class MnemonicClient {
    companion object {
        // 生成助记词，获取 DbChainKey
        fun generateMnemonic(): DbChainKey {
            var dbChainKey: DbChainKey
            do {
                dbChainKey = Wallet.generateMnemonic("walletName", "walletPassword")
            } while (dbChainKey.publicKey64.length != 128)
            return dbChainKey
        }

        // 导入助记词，获取 DbChainKey
        fun importMnemonic(list: List<String>): DbChainKey {
            return Wallet.importMnemonic(list, "walletPassword")
        }

        fun mnemonic2list(mnemonic: String?): List<String> {
            mnemonic ?: return emptyList()
            return mnemonic.trim().toLowerCase(Locale.ROOT).split(" ")
        }

        fun checkMnemonic(mnemonic: String?): Boolean {
            mnemonic ?: return false
            val list = mnemonic2list(mnemonic)
            return checkMnemonic(list)
        }

        fun checkMnemonic(mnemonic: List<String>): Boolean {
            if (mnemonic.size != 12) return false
            val inputStream = NetworkLib.mContext.assets.open("bip39-wordlist.txt")
            val br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            val wordList = ArrayList<String>(2048)
            var word: String?
            while (br.readLine().also { word = it } != null) {
                wordList.add(word!!)
            }
            br.close()
            mnemonic.forEach {
                val contains = wordList.contains(it)
                if (!contains) {
                    return false
                }
            }
            return true
        }
    }
}
