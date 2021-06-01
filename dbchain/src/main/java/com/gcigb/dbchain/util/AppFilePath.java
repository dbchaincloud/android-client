package com.gcigb.dbchain.util;

import android.content.Context;

public class AppFilePath {
    // KeyStore文件外置存储目录
    public static String Wallet_DIR;

    public static void init(Context context) {
        Wallet_DIR = context.getFilesDir().getAbsolutePath();
    }
}
