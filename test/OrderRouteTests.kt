package com.taufiq.api

import io.ktor.server.testing.*
import org.junit.Test
import io.ktor.http.*
import kotlin.test.assertEquals

class OrderRouteTests {
    @Test
    fun testGetOrder() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order/2021-08-30-02").apply {
                assertEquals(
                    """{"number":"2021-08-30-02","content":[{"item":"TV","amount":2,"price":10.0},{"item":"Hardisk","amount":2,"price":10.0},{"item":"Book","amount":2,"price":1.75},{"item":"Bag","amount":2,"price":2.3}]}""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
