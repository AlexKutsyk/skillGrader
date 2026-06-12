package com.otus.otuskotlin.skillGrader.project.common.helpers

import com.otus.otuskotlin.skillGrader.project.common.models.AppError

fun Throwable.asAppError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = AppError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)