package com.otus.otuskotlin.skillGrader.project.ktor.v1

import com.otus.otuskotlin.skillGrader.api.v1.models.*
import com.otus.otuskotlin.skillGrader.project.ktor.ServiceSettings
import io.ktor.server.application.*
import kotlin.reflect.KClass

val classCreate: KClass<*> = ApplicationCall::createRule::class
suspend fun ApplicationCall.createRule(appSettings: ServiceSettings) =
    processV1<RuleCreateRequest, RuleCreateResponse>(appSettings, classCreate,"create")

val classRead: KClass<*> = ApplicationCall::readRule::class
suspend fun ApplicationCall.readRule(appSettings: ServiceSettings) =
    processV1<RuleReadRequest, RuleReadResponse>(appSettings, classRead, "read")

val classUpdate: KClass<*> = ApplicationCall::updateRule::class
suspend fun ApplicationCall.updateRule(appSettings: ServiceSettings) =
    processV1<RuleUpdateRequest, RuleUpdateResponse>(appSettings, classUpdate, "update")

val classDelete: KClass<*> = ApplicationCall::deleteRule::class
suspend fun ApplicationCall.deleteRule(appSettings: ServiceSettings) =
    processV1<RuleDeleteRequest, RuleDeleteResponse>(appSettings, classDelete, "delete")

val classSearch: KClass<*> = ApplicationCall::searchRule::class
suspend fun ApplicationCall.searchRule(appSettings: ServiceSettings) =
    processV1<RuleSearchRequest, RuleSearchResponse>(appSettings, classSearch, "search")