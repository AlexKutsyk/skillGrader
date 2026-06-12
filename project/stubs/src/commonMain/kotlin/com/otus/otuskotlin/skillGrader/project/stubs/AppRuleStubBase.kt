package com.otus.otuskotlin.skillGrader.project.stubs

import com.otus.otuskotlin.skillGrader.project.common.models.*

object AppRuleStubBase {
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