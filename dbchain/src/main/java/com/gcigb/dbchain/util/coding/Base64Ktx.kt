package com.gcigb.dbchain.util.coding

import android.util.Base64
import com.gcigb.dbchain.util.coding.HexUtil

/**
 * @author: Xiao Bo
 * @date: 24/10/2020
 */

fun base64Encode(bytes: ByteArray): String {
    return Base64.encodeToString(bytes, Base64.NO_WRAP)
}

fun base64Decode(str: String): ByteArray {
    return Base64.decode(str, Base64.NO_WRAP)
}

fun base64EncodeByHexString(str: String): String {
    return base64Encode(HexUtil.decode(str))
}
