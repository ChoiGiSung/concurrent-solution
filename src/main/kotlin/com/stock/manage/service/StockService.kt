package com.stock.manage.service

import com.stock.manage.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockRepository: StockRepository
) {

    fun decrease(id :Long, quantity: Long){
        // get stock
        // 재고 감소
        // 저장

        val stock = stockRepository.findById(id).orElseThrow()
        stock.decrease(quantity)
        stockRepository.saveAndFlush(stock)
    }
}