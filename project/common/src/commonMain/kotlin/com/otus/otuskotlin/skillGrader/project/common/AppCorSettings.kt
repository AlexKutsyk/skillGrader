package com.otus.otuskotlin.skillGrader.project.common

import com.otus.otuskotlin.skillGrader.logging.common.LoggerProvider

data class AppCorSettings(
    val loggerProvider: LoggerProvider = LoggerProvider()
) {
    companion object {
        val NONE = AppCorSettings()
    }
}