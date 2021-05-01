package com.example.dindinapp.mocks

import android.util.Log
import com.example.dindinapp.BuildConfig
import okhttp3.*
import java.lang.IllegalArgumentException

// Created by Gbenga Oladipupo(Devmike01) on 5/1/21.


class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG){
            val uri = chain.request().url().uri().toString()
            Log.d("MockInterceptor", "URL -> $uri")
            val responseString = when{
                uri.endsWith("starred") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(SUCCESS_CODE)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(ResponseBody.create(MediaType.parse("application/json"),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        }else{
            throw IllegalArgumentException("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }


    companion object{
        const val SUCCESS_CODE: Int = 200

        const val getListOfReposBeingStarredJson = """
        [{
	"id": 1296269,
	"node_id": "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
	"name": "Hello-World",
	"full_name": "octocat/Hello-World",
	"private": false,
	"html_url": "https://github.com/octocat/Hello-World",
	"description": "This your first repo!",
	"fork": false,
	"languages_url": "http://api.github.com/repos/octocat/Hello-World/languages",
	"stargazers_count": 80,
	"watchers_count": 80,
	"pushed_at": "2011-01-26T19:06:43Z",
	"created_at": "2011-01-26T19:01:12Z",
	"updated_at": "2011-01-26T19:14:43Z",
	"subscribers_count": 42
}]
"""
    }

}