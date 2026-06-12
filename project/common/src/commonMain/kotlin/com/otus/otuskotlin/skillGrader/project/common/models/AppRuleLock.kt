package com.otus.otuskotlin.skillGrader.project.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class AppRuleLock(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = AppRuleLock("")
    }
}