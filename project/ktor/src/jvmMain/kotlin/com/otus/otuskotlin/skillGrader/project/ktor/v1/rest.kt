package com.otus.otuskotlin.skillGrader.project.ktor.v1

import com.otus.otuskotlin.skillGrader.project.ktor.ServerSettings
import io.ktor.server.routing.*

fun Route.v1Rule(appSettings: ServerSettings) {
    route("rule") {
        post("create") {
            call.createRule(appSettings)
        }
        post("read") {
            call.readRule(appSettings)
        }
        post("update") {
            call.updateRule(appSettings)
        }
        post("delete") {
            call.deleteRule(appSettings)
        }
        post("search") {
            call.searchRule(appSettings)
        }
    }
}
