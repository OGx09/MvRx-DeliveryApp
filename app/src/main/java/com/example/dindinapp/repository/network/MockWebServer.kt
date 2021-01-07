package com.example.dindinapp.repository.network

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


class MockWebServer {

    fun mock(): HttpUrl{
        val response = MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setBody("{\n" +
                    "\t\"categories\": [\n" +
                    "\t\t{\n" +
                    "\t\t\"id\": 1,\n" +
                    "\t\t\"name\":\"Sushi\",\n" +
                    "\t\t\"filter\":[{\n" +
                    "\t\t\t\"name\": \"Spicy\",\n" +
                    "\t\t\t\"id\": 1,\n" +
                    "\t\t\t\"menu\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\": \"Non Spicy\",\n" +
                    "\t\t\t\"id\": 2,\n" +
                    "\t\t\t\"menu\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}]\n" +
                    "\t},\n" +
                    "\t\n" +
                    "\t{\n" +
                    "\t\t\"id\": 2,\n" +
                    "\t\t\"name\":\"Drinks\",\n" +
                    "\t\t\"filter\":[{\n" +
                    "\t\t\t\"name\": \"Wine\",\n" +
                    "\t\t\t\"id\": 1,\n" +
                    "\t\t\t\"menu\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"name\":\"Tesla Tequila\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\": \"Beer\",\n" +
                    "\t\t\t\"id\": 2,\n" +
                    "\t\t\t\"menu\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}]\n" +
                    "\t},\n" +
                    "{\n" +
                    "\t\t\"id\": 3,\n" +
                    "\t\t\"name\":\"Sushi\",\n" +
                    "\t\t\"filter\":[{\n" +
                    "\t\t\t\"name\": \"Beer\",\n" +
                    "\t\t\t\"id\": 1,\n" +
                    "\t\t\t\"menu\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"name\":\"Nigiri\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Narezushi\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Sasazushi\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\": \"Wine\",\n" +
                    "\t\t\t\"id\": 2,\n" +
                    "\t\t\t\"menu\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"name\":\"Hawaiian\",\n" +
                    "\t\t\t\"recipe\": [\"Chicken\", \"Pineapple\", \"Domino's sauce\"],\n" +
                    "\t\t\t\"size\" : \"200 grams, 35cm\"\n" +
                    "\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}]\n" +
                    "\t}\n" +
                    "\t]\n" +
                    "}")

        val mockWebServer = MockWebServer()
        mockWebServer.enqueue(response)
        mockWebServer.start()
        return mockWebServer.url("/v1")
    }
}