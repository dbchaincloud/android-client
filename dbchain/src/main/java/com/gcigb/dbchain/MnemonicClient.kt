package com.gcigb.dbchain

import com.gcigb.dbchain.util.Wallet

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
    }
}