package com.example.dindinapp.test

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


// Created by Gbenga Oladipupo(Devmike01) on 1/12/21.


class RetrofitMockClient(statusCode: Int, reason: String, jsonResponse: String)  {
   // private val jsonResponse: String
    private val statusCode = 200
    //private val reason: String
   // @Throws(IOException::class)


//    fun execute(request: Request): Response {
//        return createDummyJsonResponse(request.getUrl(), statusCode, reason, jsonResponse)
//    }
//
//    private fun createDummyJsonResponse(
//        url: String,
//        responseCode: Int,
//        reason: String,
//        json: String
//    ): Response {
//        return Response(
//            url, responseCode, reason, Collections.EMPTY_LIST,
//            TypedByteArray(MIME_TYPE, json.toByteArray())
//        )
//    }
//
//    companion object {
//        private const val MIME_TYPE = "application/json"
//    }
//
//    init {
//        this.statusCode = statusCode
//        this.reason = reason
//        this.jsonResponse = jsonResponse
//    }
}