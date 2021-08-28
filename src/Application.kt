package com.taufiq.api

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.http.HttpHeaders.Accept
import io.netty.handler.codec.http.HttpMethod.GET

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {


        }
    }

    val client = HttpClient(Jetty) {
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/taufiq/api") {
            val firstName = call.request.queryParameters["firstName"]
            val lastName = call.request.queryParameters["lastName"]

            if (firstName.isNullOrBlank() && lastName.isNullOrEmpty()) {
                call.respond(
                    mapOf(
                        "message" to "not define any query parameters."
                    )
                )
            } else
                call.respond(
                    mapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "messagge" to "Hello $firstName $lastName"
                    )
                )
        }
    }
}

