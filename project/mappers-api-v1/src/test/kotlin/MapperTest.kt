import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateRequest
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleCreateResponse
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleDebug
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleRequestDebugMode
import com.otus.otuskotlin.skillGrader.api.v1.models.RuleRequestDebugStubs
import com.otus.otuskotlin.skillGrader.common.AppContext
import com.otus.otuskotlin.skillGrader.common.models.AppCommand
import com.otus.otuskotlin.skillGrader.common.models.AppError
import com.otus.otuskotlin.skillGrader.common.models.AppRequestId
import com.otus.otuskotlin.skillGrader.common.models.AppRuleId
import com.otus.otuskotlin.skillGrader.common.models.AppRuleLock
import com.otus.otuskotlin.skillGrader.common.models.AppState
import com.otus.otuskotlin.skillGrader.common.models.AppWorkMode
import com.otus.otuskotlin.skillGrader.common.stubs.AppStubs
import com.otus.otuskotlin.skillGrader.mappers.v1.fromTransport
import com.otus.otuskotlin.skillGrader.mappers.v1.toTransportCreateRule
import com.otus.otuskotlin.skillGrader.mappers.v1.toTransportRule
import com.otus.otuskotlin.skillGrader.stubs.AppRuleStub
import org.junit.Test
import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val request = RuleCreateRequest(
            debug = RuleDebug(
                mode = RuleRequestDebugMode.STUB,
                stub = RuleRequestDebugStubs.SUCCESS,
            ),
            requestRule = AppRuleStub.get().toTransportCreateRule()
        )
        val expected = AppRuleStub.prepareResult {
            id = AppRuleId.NONE
            lock = AppRuleLock.NONE
            permissionsClient.clear()
        }

        val context = AppContext()
        context.fromTransport(request)

        assertEquals(AppStubs.SUCCESS, context.stubCase)
        assertEquals(AppWorkMode.STUB, context.workMode)
        assertEquals(expected, context.ruleRequest)
    }

    @Test
    fun toTransport() {
        val context = AppContext(
            requestId = AppRequestId("000"),
            command = AppCommand.CREATE,
            ruleResponse = AppRuleStub.get(),
            errors = mutableListOf(
                AppError(
                    code = "error",
                    group = "request",
                    field = "name",
                    message = "wrong name",
                )
            ),
            state = AppState.RUNNING,
        )

        val req = context.toTransportRule() as RuleCreateResponse

        assertEquals(req.rule, AppRuleStub.get().toTransportRule())
        assertEquals(1, req.errors?.size)
        assertEquals("error", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("name", req.errors?.firstOrNull()?.field)
        assertEquals("wrong name", req.errors?.firstOrNull()?.message)
    }
}