package com.otus.otuskotlin.skillGrader.stubs

import com.otus.otuskotlin.skillGrader.common.models.AppGrade
import com.otus.otuskotlin.skillGrader.common.models.AppRule
import com.otus.otuskotlin.skillGrader.common.models.AppRuleId
import com.otus.otuskotlin.skillGrader.common.models.AppRuleLock
import com.otus.otuskotlin.skillGrader.common.models.AppRulePermissionClient

object AppRuleStubExample {
    val RULE_STUB: AppRule
        get() = AppRule(
            id = AppRuleId("007"),
            name = "Beginner A1",
            grade = AppGrade.A1,
            minGrammarPercent = 20,
            minLexiconsPercent = 20,
            minListeningPercent = 20,
            timeWindowDays = 0,
            priority = 1,
            lock = AppRuleLock("123"),
            permissionsClient = mutableSetOf(
                AppRulePermissionClient.READ,
                AppRulePermissionClient.UPDATE,
                AppRulePermissionClient.DELETE,
            )
        )
}