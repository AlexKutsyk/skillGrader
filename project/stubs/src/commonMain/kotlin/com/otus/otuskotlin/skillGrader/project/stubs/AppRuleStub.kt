package com.otus.otuskotlin.skillGrader.project.stubs

import com.otus.otuskotlin.skillGrader.project.common.models.AppGrade
import com.otus.otuskotlin.skillGrader.project.common.models.AppRule
import com.otus.otuskotlin.skillGrader.project.common.models.AppRuleId

object AppRuleStub {
    fun get(): AppRule = AppRuleStubExample.RULE_STUB.copy()

    fun prepareResult(block: AppRule.() -> Unit): AppRule = get().apply(block)

    private fun appRule(base: AppRule, id: String, filter: String, grade: AppGrade) = base.copy(
        id = AppRuleId(id),
        name = "$filter $grade",
        grade = grade,
    )
}