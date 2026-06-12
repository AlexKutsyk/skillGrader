package com.otus.otuskotlin.skillGrader.project.ktor.stub

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.otus.otuskotlin.skillGrader.api.v1.models.Grade
import com.otus.otuskotlin.skillGrader.api.v1.models.IRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDebug
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDeleteObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDeleteRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDeleteResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleReadObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleReadRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleReadResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleRequestDebugMode
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleRequestDebugStubs
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleSearchFilter
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleSearchRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleSearchResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleUpdateObject
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleUpdateRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleUpdateResponse
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings
import com.otus.otuskotlin.skillGrader.project.ktor.ServiceSettings
import com.otus.otuskotlin.skillGrader.project.ktor.moduleJvm
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class V1RuleStubApiTest {
    @Test
    fun create() = v1TestApplication(
        function = "create",
        request = RuleCreateRequest(
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
        ),
    ) { response ->
        val responseObj = response.body<RuleCreateResponse>()
        assertEquals(200, response.status.value)
        assertEquals("007", responseObj.rule?.id)
    }

    @Test
    fun read() = v1TestApplication(
        function = "read",
        request = RuleReadRequest(
            requestRule = RuleReadObject("007"),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleReadResponse>()
        assertEquals(200, response.status.value)
        assertEquals("007", responseObj.rule?.id)
    }

    @Test
    fun update() = v1TestApplication(
        function = "update",
        request = RuleUpdateRequest(
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
        ),
    ) { response ->
        val responseObj = response.body<RuleUpdateResponse>()
        assertEquals(200, response.status.value)
        assertEquals("007", responseObj.rule?.id)
    }

    @Test
    fun delete() = v1TestApplication(
        function = "delete",
        request = RuleDeleteRequest(
            requestRule = RuleDeleteObject(
                id = "007",
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleDeleteResponse>()
        assertEquals(200, response.status.value)
        assertEquals("007", responseObj.rule?.id)
    }

    @Test
    fun search() = v1TestApplication(
        function = "search",
        request = RuleSearchRequest(
            ruleFilter = RuleSearchFilter(),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleSearchResponse>()
        assertEquals(200, response.status.value)
        assertEquals("001", responseObj.rules?.first()?.id)
    }

    private fun v1TestApplication(
        function: String,
        request: IRequest,
        action: suspend (HttpResponse) -> Unit,
    ): Unit = testApplication {
        application { moduleJvm(ServiceSettings(corSettings = AppCorSettings())) }
        val client = createClient {
            install(ContentNegotiation) {
                jackson {
                    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

                    enable(SerializationFeature.INDENT_OUTPUT)
                    writerWithDefaultPrettyPrinter()
                }
            }
        }
        val response = client.post("/v1/rule/$function") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        action(response)
    }
}
