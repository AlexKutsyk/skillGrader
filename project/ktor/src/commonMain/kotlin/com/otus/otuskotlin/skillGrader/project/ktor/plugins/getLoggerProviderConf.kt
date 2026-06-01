package com.otus.otuskotlin.skillGrader.project.ktor.plugins

import com.otus.otuskotlin.skillGrader.logging.common.LoggerProvider
import io.ktor.server.application.*

expect fun Application.getLoggerProviderConf(): LoggerProvider
