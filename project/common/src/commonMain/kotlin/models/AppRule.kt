package com.otus.otuskotlin.skillGrader.common.models

data class AppRule(
    var id: AppRuleId = AppRuleId.NONE,
    var name: String = "",
    var description: String = "",
    var lock: AppRuleLock = AppRuleLock.NONE,
    val permissionsClient: MutableSet<AppRulePermissionClient> = mutableSetOf(),
) {
    fun isEmpty() = this == NONE

    companion object {
        private val NONE = AppRule()
    }
}