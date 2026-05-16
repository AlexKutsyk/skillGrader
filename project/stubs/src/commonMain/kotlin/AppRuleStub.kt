package com.otus.otuskotlin.skillGrader.stubs

import com.otus.otuskotlin.skillGrader.common.models.AppGrade
import com.otus.otuskotlin.skillGrader.common.models.AppRule
import com.otus.otuskotlin.skillGrader.common.models.AppRuleId
import com.otus.otuskotlin.skillGrader.stubs.AppRuleStubExample.RULE_STUB

object AppRuleStub {
    fun get(): AppRule = RULE_STUB.copy()

    fun prepareResult(block: AppRule.() -> Unit): AppRule = get().apply(block)

    private fun appRule(base: AppRule, id: String, filter: String, grade: AppGrade) = base.copy(
        id = AppRuleId(id),
        name = "$filter $grade",
        grade = grade,
    )
}