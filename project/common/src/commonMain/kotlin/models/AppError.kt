package com.otus.otuskotlin.skillGrader.common.models

import com.otus.otuskotlin.skillGrader.logging.common.LogLevel

data class AppError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val level: LogLevel = LogLevel.ERROR,
    val exception: Throwable? = null,
)