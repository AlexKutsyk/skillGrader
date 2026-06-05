package com.otus.otuskotlin.skillGrader.project.ktor.websocket

import com.otus.otuskotlin.skillGrader.api.v1.models.Grade
import com.otus.otuskotlin.skillGrader.api.v1.models.IRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.IResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.ResponseResult
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDebug
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDeleteObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDeleteRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleInitResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleReadObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleReadRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleRequestDebugMode
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleRequestDebugStubs
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleSearchFilter
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleSearchRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleUpdateObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleUpdateRequest
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings
import com.otus.otuskotlin.skillGrader.project.ktor.ServiceSettings
import com.otus.otuskotlin.skillGrader.project.ktor.moduleJvm
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import kotlinx.coroutines.withTimeout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.time.Duration.Companion.seconds

class V1WebsocketStubTest {

    @Test
    fun createStub() {
        val request = RuleCreateRequest(
            requestRule  = RuleCreateObject(
                name = "A1 kids",
                grade = Grade.A1,
                minGrammarPercent = 10,
                minLexiconsPercent = 10,
                minListeningPercent = 10,
                timeWindowDays = 0,
                priority = 1
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun readStub() {
        val request = RuleReadRequest(
            requestRule = RuleReadObject("666"),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun updateStub() {
        val request = RuleUpdateRequest(
            requestRule = RuleUpdateObject(
                id = "007",
                minGrammarPercent = 5,
                minLexiconsPercent = 5,
                minListeningPercent = 5,
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun deleteStub() {
        val request = RuleDeleteRequest(
            requestRule = RuleDeleteObject(
                id = "666",
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun searchStub() {
        val request = RuleSearchRequest(
            ruleFilter = RuleSearchFilter(),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    private inline fun <reified T> testMethod(
        request: IRequest,
        crossinline assertBlock: (T) -> Unit
    ) = testApplication {
        application { moduleJvm(ServiceSettings(corSettings = AppCorSettings())) }
        val client = createClient {
            install(WebSockets) {
                contentConverter = JacksonWebsocketContentConverter()
            }
        }

        client.webSocket("/v1/ws") {
            withTimeout(3.seconds) {
                val response = receiveDeserialized<IResponse>() as T
                assertIs<RuleInitResponse>(response)
            }
            sendSerialized(request)
            withTimeout(3.seconds) {
                val response = receiveDeserialized<IResponse>() as T
                assertBlock(response)
            }
        }
    }
}