package com.taufiq.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Order (val number: String, val content: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

val orderStorage = listOf(
    Order("2021-08-29-01", listOf(
    OrderItem("Keyboard",1,10.0),
    OrderItem("Mouse",1,5.0),
    OrderItem("Monitor",2,20.0),
    OrderItem("Motherboard",1,100.0),
    OrderItem("Ram",2,150.0),
    OrderItem("Vga",1,50.0)
)),
    Order("2021-08-30-02", listOf(
    OrderItem("TV",2,10.0),
    OrderItem("Hardisk",2,10.0),
    OrderItem("Book",2,1.75),
    OrderItem("Bag",2,2.30),
)))
