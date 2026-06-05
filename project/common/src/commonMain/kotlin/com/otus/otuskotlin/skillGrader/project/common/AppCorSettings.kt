package com.otus.otuskotlin.skillGrader.project.common

import com.otus.otuskotlin.skillGrader.logging.common.LoggerProvider
import com.otus.otuskotlin.skillGrader.project.common.ws.IWsSessionRepo

data class AppCorSettings(
    val loggerProvider: LoggerProvider = LoggerProvider(),
    val wsSessions: IWsSessionRepo = IWsSessionRepo.NONE,
) {
    companion object {
        val NONE = AppCorSettings()
    }
}