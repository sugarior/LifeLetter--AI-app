package com.example.lifeletter


import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object HttpHelper {

    const val API_ENDPOINT = "https://api.moonshot.cn/v1/chat/completions"
    const val API_KEY = "sk-bgV9rHr9JeWm52U48lbBJdpSLIJNE5p3knt2l6JVFOhou32E"

    @JvmStatic
    fun sendToRobot(history: String): String? {
        try {
            //网络代理
            //val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("127.0.0.1", 7890))
            //val conn:HttpURLConnection = URL(API_ENDPOINT).openConnection(proxy) as HttpURLConnection
            val conn: HttpURLConnection = URL(API_ENDPOINT).openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("Authorization", "Bearer $API_KEY")
            conn.connectTimeout = 20 * 1000
            conn.doOutput = true

            // 构建请求体
            val data = mutableMapOf<String, Any>()
            data["model"] = "moonshot-v1-8k"

            val messages = mutableListOf<Map<String, String>>()
            messages.add(mapOf("role" to "system", "content" to "你是小信，由 搅和 提供的人工智能助手，你更擅长中文和英文的对话。你会为用户提供安全，有帮助，准确的回答。同时，你会拒绝一切涉及恐怖主义，种族歧视，黄色暴力等问题的回答。小信为专有名词，不可翻译成其他语言。你将提取用户问题中的事件，事件包含事件名称，事件时间，事件地点，事件可能有多个，并严格按以下格式返回答案:*活动编号*：一个整数\n" +
                    "*活动名称*：事件名称\n" +
                    "*活动时间*：事件时间\n" +
                    "*活动地点*：事件地点"))
            messages.add(mapOf("role" to "user", "content" to history))

            data["messages"] = messages
            data["temperature"] = 0.7

            val gson = Gson()
            val requestBody:String = gson.toJson(data)

            // 发送请求
            conn.getOutputStream().write(requestBody.toByteArray(StandardCharsets.UTF_8))

            // 读取响应
            val reader = BufferedReader(InputStreamReader(conn.getInputStream()))
            val response = StringBuilder()
            var line: String?
            while (true) {
                line = reader.readLine()
                if (line == null) break
                response.append(line)
            }
            reader.close()
            val res = response.toString()
            Log.e(AskActivity.ACTIVITY_SERVICE, "res: ${res}")
            val resObj = JSONObject(res)
            val retMessage = resObj.getJSONArray("choices").get(0) as JSONObject
            return retMessage.getJSONObject("message").getString("content")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e(AskActivity.ACTIVITY_SERVICE, "error: ${e.message}")
        }
        return null
    }
}
