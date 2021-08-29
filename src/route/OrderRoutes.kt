package com.taufiq.api.route

import com.taufiq.api.model.orderStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

/** GET http://0.0.0.0:8080/order/
Content-Type: application/json

GET http://0.0.0.0:8080/order/{id}
Content-Type: application/json

GET http://0.0.0.0:8080/order/{id}/total
Content-Type: application/json
**/

fun Route.listOfOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        } else {
            call.respond(status = HttpStatusCode.BadRequest, "Check your url correctly")
        }
    }

}

fun Route.getOrderRoute(){
    get("/order/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText("Not Found!", status =
        HttpStatusCode.NotFound
        )

        call.respond(order)
    }

}

fun Route.totalOrderRoute() {
    get("/order/{id}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad request", status =
        HttpStatusCode.BadRequest)

        val order = orderStorage.find { it.number == id } ?: return@get call.respondText("Not found!", status =
        HttpStatusCode.NotFound)

        val total = order.content.sumOf { it.price * it.amount }
        call.respond(mapOf("total" to total))
    }
}



fun Application.registerOrderRoutes() {
    routing {
        listOfOrdersRoute()
        getOrderRoute()
        totalOrderRoute()
    }
}
