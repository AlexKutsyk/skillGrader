package com.otus.otuskotlin.skillGrader.common

import com.otus.otuskotlin.skillGrader.common.models.AppCommand
import com.otus.otuskotlin.skillGrader.common.models.AppRequestId
import com.otus.otuskotlin.skillGrader.common.models.AppError
import com.otus.otuskotlin.skillGrader.common.models.AppState
import com.otus.otuskotlin.skillGrader.common.models.AppWorkMode
import com.otus.otuskotlin.skillGrader.common.models.AppRule
import com.otus.otuskotlin.skillGrader.common.models.AppRuleFilter
import com.otus.otuskotlin.skillGrader.common.stubs.AppStubs
import kotlin.time.Instant

data class AppContext(
    var command: AppCommand = AppCommand.NONE,
    var state: AppState = AppState.NONE,
    val errors: MutableList<AppError> = mutableListOf(),

    var workMode: AppWorkMode = AppWorkMode.PROD,
    var stubCase: AppStubs = AppStubs.NONE,

    var requestId: AppRequestId = AppRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var adRequest: AppRule = AppRule(),
    var adFilterRequest: AppRuleFilter = AppRuleFilter(),

    var adResponse: AppRule = AppRule(),
    var adsResponse: MutableList<AppRule> = mutableListOf(),
)