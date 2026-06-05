package com.otus.otuskotlin.skillGrader.project.ktor.base

import com.otus.otuskotlin.skillGrader.api.v1.models.IResponse
import com.otus.otuskotlin.skillGrader.project.common.ws.IWsSession
import com.otus.otuskotlin.skillGrader.project.jackson.apiV1ResponseSerialize
import io.ktor.websocket.*

data class KtorWsSessionV1(
    private val session: WebSocketSession
) : IWsSession {
    override suspend fun <T> send(obj: T) {
        require(obj is IResponse)
        session.send(Frame.Text(apiV1ResponseSerialize(obj)))
    }
}
