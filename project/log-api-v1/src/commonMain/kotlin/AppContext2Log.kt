package com.otus.otuskotlin.skillGrader.project.log.api.v1

import com.otus.otuskotlin.skillGrader.project.common.AppContext
import com.otus.otuskotlin.skillGrader.project.common.models.AppError
import com.otus.otuskotlin.skillGrader.project.common.models.AppGrade
import com.otus.otuskotlin.skillGrader.project.common.models.AppRequestId
import com.otus.otuskotlin.skillGrader.project.common.models.AppRule
import com.otus.otuskotlin.skillGrader.project.common.models.AppRuleFilter
import com.otus.otuskotlin.skillGrader.project.common.models.AppRuleId
import com.otus.otuskotlin.skillGrader.project.log.api.v1.models.*

import kotlin.time.Clock

fun AppContext.toLog(logId: String) = CommonLogModel(
    messageTime = Clock.System.now().toString(),
    logId = logId,
    source = "skillGrader",
    rule = toAppLog(),
    errors = errors.map { it.toLog() },
)

private fun AppContext.toAppLog(): AppLogModel? {
    val ruleNone = AppRule()
    return AppLogModel(
        requestId = requestId.takeIf { it != AppRequestId.NONE }?.asString(),
        requestRule = ruleRequest.takeIf { it != ruleNone }?.toLog(),
        responseRule = ruleResponse.takeIf { it != ruleNone }?.toLog(),
        requestFilter = ruleFilterRequest.takeIf { it != AppRuleFilter() }?.toLog(),
    ).takeIf { it != AppLogModel() }
}

private fun AppRuleFilter.toLog() = RuleFilterLog(
    searchString = searchString.takeIf { it.isNotBlank() },
)

private fun AppError.toLog() = ErrorLogModel(
    message = message.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    code = code.takeIf { it.isNotBlank() },
    level = level.name,
)

private fun AppRule.toLog() = RuleLog(
    id = id.takeIf { it != AppRuleId.NONE }?.asString(),
    name = name.takeIf { it.isNotBlank() },
    grade = grade.takeIf { it != AppGrade.NONE }?.name,
    minGrammarPercent = minGrammarPercent.takeIf { it != 0 && it <= 100 },
    minLexiconsPercent = minLexiconsPercent.takeIf { it != 0 && it <= 100 },
    minListeningPercent = minListeningPercent.takeIf { it != 0 && it <= 100 },
    timeWindowDays = timeWindowDays,
    priority = priority,
    permissions = permissionsClient.takeIf { it.isNotEmpty() }?.map { it.name }?.toSet(),
)