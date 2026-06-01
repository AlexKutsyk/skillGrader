package com.otus.otuskotlin.skillGrader.project.ktor

import com.otus.otuskotlin.skillGrader.project.app.common.IServerSettings
import com.otus.otuskotlin.skillGrader.project.biz.RuleProcessor
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings

data class ServerSettings(
    val appUrls: List<String> = emptyList(),
    override val corSettings: AppCorSettings = AppCorSettings(),
    override val processor: RuleProcessor = RuleProcessor(corSettings),
): IServerSettings