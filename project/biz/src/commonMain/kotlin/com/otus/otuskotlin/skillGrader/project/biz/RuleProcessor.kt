package com.otus.otuskotlin.skillGrader.project.biz

import com.otus.otuskotlin.skillGrader.project.common.AppContext
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings
import com.otus.otuskotlin.skillGrader.project.common.models.AppState
import com.otus.otuskotlin.skillGrader.project.stubs.AppRuleStub

@Suppress("unused", "RedundantSuspendModifier")
class RuleProcessor(val corSettings: AppCorSettings) {

    suspend fun execute(ctx: AppContext) {
        ctx.ruleResponse = AppRuleStub.get()
//        ctx.adsResponse = MkplAdStub.prepareSearchList("ad search", MkplDealSide.DEMAND).toMutableList()
        ctx.state = AppState.RUNNING
    }
}