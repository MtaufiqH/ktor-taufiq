package com.taufiq.api.route

import com.taufiq.api.model.customerStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.customerRouting() {
    route("/customers") {

        get {
            // get all customers
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customer found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            // get customer by id (singular)
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id $id",
            status = HttpStatusCode.NotFound)

            call.respond(customer)
        }

        post {
            // input customer
            val customer = call
        }

        delete("{id}") {
            // delete customer by id
        }

    }

}