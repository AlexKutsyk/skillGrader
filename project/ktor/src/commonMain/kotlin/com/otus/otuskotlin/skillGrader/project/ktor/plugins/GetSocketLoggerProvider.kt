package com.otus.otuskotlin.skillGrader.project.ktor.plugins

import com.otus.otuskotlin.skillGrader.libs.lib.logging.socket.SocketLoggerSettings
import com.otus.otuskotlin.skillGrader.libs.lib.logging.socket.loggerSocket
import com.otus.otuskotlin.skillGrader.logging.common.LoggerProvider
import io.ktor.server.application.*

fun Application.getSocketLoggerProvider(): LoggerProvider {
    val loggerSettings = environment.config.config("ktor.socketLogger").let { conf ->
        SocketLoggerSettings(
            host = conf.propertyOrNull("host")?.getString() ?: "127.0.0.1",
            port = conf.propertyOrNull("port")?.getString()?.toIntOrNull() ?: 9002,
        )
    }
    return LoggerProvider { loggerSocket(it, loggerSettings) }
}
