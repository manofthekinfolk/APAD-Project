package com.example.getappengine

import okhttp3.*
import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import android.app.PendingIntent.getActivity

import java.util.HashMap

import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class OkHttpRequest(client: OkHttpClient) {

    internal var client = OkHttpClient()

    init {
        this.client = client
    }

    fun GET(url: String, callback: Callback) : Call {
        val request = Request.Builder()
            .header("User-Agent", "Android")
            .url(url)
            .build()

        //val response = client.newCall(request).execute()
        //val bodystr = response.body()?.string()
        //return bodystr
        val call = client.newCall(request)
        call.enqueue(callback)
        return call

    }

    fun POST(url: String, parameters: HashMap<String, String>) : String? {
        val client = OkHttpClient()
//        val request = OkHttpRequest(client)

        val builder = FormBody.Builder()
        val it = parameters.entries.iterator()

        while(it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>
            builder.add(pair.key.toString(), pair.value.toString())
        }

        val formBody = builder.build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val response = client.newCall(request).execute()
        val bodystr = response?.body()?.string()
        return bodystr
    }

    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }
}

//package com.example.getappengine

/*
import okhttp3.*

class OkHttpRequest(client: OkHttpClient) {


    internal var client = OkHttpClient()

    init {
        this.client = client
    }

    fun GET(url: String, callback: Callback) : String? {
        val request = Request.Builder()
            .header("User-Agent", "Android")
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val bodystr = response.body()?.string()
        //return bodystr
        //val call = client.newCall(request)
       // call.enqueue(callback)
        return bodystr

    }

    fun POST(url: String, parameters: HashMap<String, String>) : String? {
        val builder = FormBody.Builder()
        val it = parameters.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>
            builder.add(pair.key.toString(), pair.value.toString())
        }

        val formBody = builder.build()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "android")
            .post(formBody)
            .build()


        val response = client.newCall(request).execute()
        val bodystr = response.body()?.string()
        return bodystr
    }

    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }
}
/*

import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

interface OkHttpRequest {
    private val baseUrl: String
        get() = "https://my-project-1530049714771.appspot.com"

    @Throws(IOException::class)
    fun doGetRequest(path: String, callback: (json: JSONObject, code: Int) -> Unit) {
        val baseUrl = baseUrl

        val request = Request.Builder()
            .url(baseUrl + path)
            .build()

        val client = OkHttpClient()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("doGetRequest Error: ${e}")
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    val headerList = response.headers()
                    if (headerList["content-type"] == "text/html") {
                        return
                    }

                    val res = response.body()?.string()
                    var json = JSONObject(res)
                    callback(json, response.code())
                }
            })
    }

    @Throws(IOException::class)
    fun doPostRequest(path: String, json: String, callback: (json: JSONObject, code: Int) -> Unit) {
        val baseUrl = baseUrl

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url(baseUrl + path)
            .post(body)
            .build()

        val client = OkHttpClient()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("doGetRequest Error: ${e}")
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    val headerList = response.headers()
                    if (headerList["content-type"] == "text/html") {
                        println("NETWORK ERROR")
                        return
                    }

                    val res = response.body()?.string()
                    var json = JSONObject(res)
                    callback(json, response.code())
                }
            })
    }
} */