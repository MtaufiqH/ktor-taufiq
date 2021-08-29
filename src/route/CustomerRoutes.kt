package com.taufiq.api.route

import com.taufiq.api.model.Customer
import com.taufiq.api.model.customerStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
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
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Customer stored correctly", status= HttpStatusCode.Created)
        }

        delete("{id}") {
            // delete customer by id
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerStorage.removeIf{ customer ->
                    customer.id == id
                }){
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }

    }

}

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}