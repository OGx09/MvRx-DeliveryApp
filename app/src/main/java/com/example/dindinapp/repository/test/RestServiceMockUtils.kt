package com.example.dindinapp.repository.test

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import kotlin.jvm.Throws

// Created by Gbenga Oladipupo(Devmike01) on 1/12/21.


object RestServiceMockUtils {

    @Throws(Exception::class)
    fun createStreamFromStream(inputStream : InputStream): String{
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line :String? = null
        while ( (bufferReader.readLine().also { line = it }) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferReader.close()
        return stringBuilder.toString()
    }

    fun getStringFromFile(context: Context, path: String): String{
        val inStream = context.resources.assets.open(path)

        val ret = createStreamFromStream(inStream)
        inStream.close()
        return ret
    }
}