package com.otus.otuskotlin.skillGrader.project.ktor

import com.otus.otuskotlin.skillGrader.project.jackson.apiV1Mapper
import com.otus.otuskotlin.skillGrader.project.ktor.plugins.initServerSettings
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.routing.*
import org.slf4j.event.Level
import com.otus.otuskotlin.skillGrader.project.ktor.v1.v1Rule
import com.otus.otuskotlin.skillGrader.project.ktor.v1.wsHandlerV1
import io.ktor.server.netty.EngineMain
import io.ktor.server.websocket.webSocket

// function with config (application.conf)
fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.moduleJvm(
    serviceSettings: ServiceSettings = initServerSettings(),
) {
    install(CachingHeaders)
    install(DefaultHeaders)
    install(AutoHeadResponse)
    install(CallLogging) { level = Level.INFO }
    module(serviceSettings)

    routing {
        route("v1") {
            install(ContentNegotiation) {
                jackson {
                    setConfig(apiV1Mapper.serializationConfig)
                    setConfig(apiV1Mapper.deserializationConfig)
                }
            }
            v1Rule(serviceSettings)
            webSocket("/ws") {
                wsHandlerV1(serviceSettings)
            }
        }
    }
}