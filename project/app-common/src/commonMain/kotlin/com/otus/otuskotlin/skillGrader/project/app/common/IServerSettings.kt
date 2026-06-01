package com.otus.otuskotlin.skillGrader.project.app.common

import com.otus.otuskotlin.skillGrader.project.biz.RuleProcessor
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings

interface IServerSettings {
    val processor: RuleProcessor
    val corSettings: AppCorSettings
}