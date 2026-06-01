package com.otus.otuskotlin.skillGrader.project.ktor.stub

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleUpdateRequest
import com.otus.otuskotlin.skillGrader.project.common.AppCorSettings
import com.otus.otuskotlin.skillGrader.project.ktor.ServerSettings
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
        func = "create",
        request = RuleCreateRequest(
            /*ad = RuleCreateObject(
                title = "Болт",
                description = "КРУТЕЙШИЙ",
                adType = DealSide.DEMAND,
                visibility = RuleVisibility.PUBLIC,
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )*/
        ),
    ) { response ->
        val responseObj = response.body<RuleCreateResponse>()
        assertEquals(200, response.status.value)
        assertEquals("666", responseObj.rule?.id)
    }

    @Test
    fun read() = v1TestApplication(
        func = "read",
        request = RuleReadRequest(
            ad = RuleReadObject("666"),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleReadResponse>()
        assertEquals(200, response.status.value)
        assertEquals("666", responseObj.ad?.id)
    }

    @Test
    fun update() = v1TestApplication(
        func = "update",
        request = RuleUpdateRequest(
            ad = RuleUpdateObject(
                id = "666",
                title = "Болт",
                description = "КРУТЕЙШИЙ",
                adType = DealSide.DEMAND,
                visibility = RuleVisibility.PUBLIC,
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleUpdateResponse>()
        assertEquals(200, response.status.value)
        assertEquals("666", responseObj.ad?.id)
    }

    @Test
    fun delete() = v1TestApplication(
        func = "delete",
        request = RuleDeleteRequest(
            ad = RuleDeleteObject(
                id = "666",
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleDeleteResponse>()
        assertEquals(200, response.status.value)
        assertEquals("666", responseObj.ad?.id)
    }

    @Test
    fun search() = v1TestApplication(
        func = "search",
        request = RuleSearchRequest(
            adFilter = RuleSearchFilter(),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleSearchResponse>()
        assertEquals(200, response.status.value)
        assertEquals("d-666-01", responseObj.ads?.first()?.id)
    }

    @Test
    fun offers() = v1TestApplication(
        func = "offers",
        request = RuleOffersRequest(
            ad = RuleReadObject(
                id = "666",
            ),
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS
            )
        ),
    ) { response ->
        val responseObj = response.body<RuleOffersResponse>()
        assertEquals(200, response.status.value)
        assertEquals("d-666-01", responseObj.ads?.first()?.id)
    }

    private fun v1TestApplication(
        func: String,
        request: IRequest,
        function: suspend (HttpResponse) -> Unit,
    ): Unit = testApplication {
        application { moduleJvm(ServerSettings(corSettings = AppCorSettings())) }
        val client = createClient {
            install(ContentNegotiation) {
                jackson {
                    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

                    enable(SerializationFeature.INDENT_OUTPUT)
                    writerWithDefaultPrettyPrinter()
                }
            }
        }
        val response = client.post("/v1/rule/$func") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        function(response)
    }
}
