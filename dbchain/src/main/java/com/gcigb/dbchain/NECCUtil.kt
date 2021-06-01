package com.gcigb.dbchain

object NECCUtil {

    init {
        System.loadLibrary("ecc")
    }

    external fun encrypt(message: String, publicKeyBase64: String): String
    external fun decrypt(message: String, privateKeyBase64: String): String
}