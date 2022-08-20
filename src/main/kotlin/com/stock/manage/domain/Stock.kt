package com.stock.manage.domain

import javax.persistence.*

@Entity
class Stock(
    val productId: Long,
    var quantity: Long
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Version
    var version: Long? = null

    fun decrease(quantity: Long) {
        val decreaseQuantity = this.quantity - quantity
        assert(decreaseQuantity >= 0)
        this.quantity = decreaseQuantity
    }
}