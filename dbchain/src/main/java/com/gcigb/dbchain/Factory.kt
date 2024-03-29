package com.gcigb.dbchain

import com.gcigb.dbchain.bean.*
import com.gcigb.dbchain.util.coding.base64Encode
import com.google.gson.Gson

fun createApplicationMessage(
    // 库名称
    name: String,
    // 库描述
    description: String,
    // 权限
    permission_required: Boolean,
    // 创建者地址
    owner: String
): Message {
    val messageValue = CreateApplicationMessageValue(name, base64Encode(description.toByteArray()), permission_required, owner)
    return Message(type = "dbchain/CreateApplication", value = messageValue)
}

fun createInsertMessage(tableName: String, fields: Map<String, String>): Message {
    val json = Gson().toJson(fields)
    val base64Encode = base64Encode(json.toByteArray())
    val messageValue = InsertMessageValue(table_name = tableName, fields = base64Encode)
    return Message(type = "dbchain/InsertRow", value = messageValue)
}

fun createFreezeMessage(tableName: String, id: String): Message {
    val messageValue = FreezeMessageValue(table_name = tableName, id = id)
    return Message(type = "dbchain/FreezeRow", value = messageValue)
}

fun createCallFunctionMessage(functionName: String, argument: String): Message {
    val messageValue = CallFunctionMessageValue(function_name = functionName, argument = argument)
    return Message(type = "dbchain/CallFunction", value = messageValue)
}

fun createAddFunctionMessage(functionName: String, description: String, body: String): Message {
    val messageValue = AddFunctionMessageValue(function_name = functionName, description = description, body = body)
    return Message(type = "dbchain/AddFunction", value = messageValue)
}