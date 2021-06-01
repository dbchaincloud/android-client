package com.dbchain.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gcigb.dbchain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val list = listOf(
        "tooth",
        "source",
        "tiny",
        "frost",
        "biology",
        "island",
        "tent",
        "alien",
        "sure",
        "easily",
        "fancy",
        "roast"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        importMnemonic(View(this))
    }

    fun generateMnemonic(view: View) {
        val dbChainKey = MnemonicClient.generateMnemonic()
        DBChain.withDBChainKey(dbChainKey)
        val mnemonic = dbChainKey.mnemonic
        // tooth source tiny frost biology island tent alien sure easily fancy roast
        Log.i(TAG, "generateMnemonic: $mnemonic")
    }

    fun importMnemonic(view: View) {
        val dbChainKey = MnemonicClient.importMnemonic(list)
        DBChain.withDBChainKey(dbChainKey)
        val mnemonic = dbChainKey.mnemonic
        Log.i(TAG, "importMnemonic: $mnemonic")
    }

    fun requestToken(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val requestAppUser = requestAppUser()
            val result = requestAppUser.isSuccess
            // 成功之后，需要等待几秒，积分才能到账
            Log.i(TAG, "requestToken: $result")
        }
    }

    fun getTokenNumber(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val token = getToken(DBChain.dbChainKey.address)
            Log.i(TAG, "getTokenNumber: $token")
        }
    }

    fun insert(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val fields = mapOf("name" to "玉皇大帝", "age" to "1000000000")
            val insertRowResult = insertRow(tableName, fields)
            Log.i(TAG, "insertResult: $insertRowResult")
        }
    }

    fun freeze(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val freezeRowResult = freezeRow(tableName, "2")
            Log.i(TAG, "freezeResult: $freezeRowResult")
        }
    }

    fun queryByArray(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val address = DBChain.dbChainKey.address
            val queriedArray = QueriedArray(table = tableName).findCreatedBy(address)
                .findEqual("name", "玉皇大帝")
            val result = querier(queriedArray)
            Log.i(TAG, "queryByArray: ${result.content}")
        }
    }


    fun queryById(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val queriedArray = QueriedArray(table = tableName).findById("3")
            val result = querier(queriedArray)
            Log.i(TAG, "queryByArray: ${result.content}")
        }
    }

    fun insertBatch(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val messageList = newMessageList().apply {
                add(createInsertMessage(tableName, mapOf("name" to "张三", "age" to "18")))
                add(createInsertMessage(tableName, mapOf("name" to "李四", "age" to "20")))
                add(createInsertMessage(tableName, mapOf("name" to "王五", "age" to "22")))
            }
            val result = handleBatchMessage(messageList)
            Log.i(TAG, "insertBatch: $result")
        }
    }

    fun freezeBatch(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val messageList = newMessageList().apply {
                add(createFreezeMessage(tableName, "1"))
                add(createFreezeMessage(tableName, "2"))
                add(createFreezeMessage(tableName, "3"))
            }
            val result = handleBatchMessage(messageList)
            Log.i(TAG, "freezeBatch: $result")
        }
    }

    fun queryAll(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val tableName = "user"
            val queriedArray = QueriedArray(table = tableName)
            val result = querier(queriedArray)
            Log.i(TAG, "queryByArray: ${result.content}")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}