package com.otus.otuskotlin.skillGrader.libs.lib.logging.socket

import com.otus.otuskotlin.skillGrader.logging.common.LogLevel
import kotlinx.serialization.Serializable

@Serializable
data class LogData(
    val level: LogLevel,
    val message: String,
//    val data: T
)
