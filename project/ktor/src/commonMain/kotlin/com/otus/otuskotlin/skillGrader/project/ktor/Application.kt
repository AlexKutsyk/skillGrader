package com.otus.otuskotlin.skillGrader.project.ktor

import com.otus.otuskotlin.skillGrader.project.ktor.plugins.initServerSettings
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.module(
    serverSettings: ServiceSettings = initServerSettings()
) {
    install(CORS) {
        allowMethod(HttpMethod.Options)
//        allowMethod(HttpMethod.Put)
//        allowMethod(HttpMethod.Delete)
//        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("MyCustomHeader")
        allowCredentials = true
        /* TODO
            Это временное решение, оно опасно.
            В боевом приложении здесь должны быть конкретные настройки
        */
        anyHost()
    }
//    install(WebSockets)
}