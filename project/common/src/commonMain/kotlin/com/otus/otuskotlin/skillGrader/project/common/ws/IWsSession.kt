package com.otus.otuskotlin.skillGrader.project.common.ws

interface IWsSession {
    suspend fun <T> send(obj: T)
    companion object {
        val NONE = object : IWsSession {
            override suspend fun <T> send(obj: T) {
            }
        }
    }
}