package com.stock.manage.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Stock(
    val productId: Long,
    var quantity: Long
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun decrease(quantity: Long) {
        val decreaseQuantity = this.quantity - quantity
        assert(decreaseQuantity >= 0)
        this.quantity = decreaseQuantity
    }
}