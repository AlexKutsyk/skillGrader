package com.otus.otuskotlin.skillGrader.project.ktor.plugins

import com.otus.otuskotlin.skillGrader.project.biz.RuleProcessor
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings
import com.otus.otuskotlin.skillGrader.project.ktor.ServiceSettings
import io.ktor.server.application.*

fun Application.initServerSettings(): ServiceSettings {
    val corSettings = AppCorSettings(
        loggerProvider = getLoggerProviderConf(),
//        wsSessions = KtorWsSessionRepo(),
    )
    return ServiceSettings(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        corSettings = corSettings,
        processor = RuleProcessor(corSettings),
    )
}
