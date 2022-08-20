package com.stock.manage.service

import com.stock.manage.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptimisticStockService(
    private val stockRepository: StockRepository
) {

    @Transactional
    fun decrease(id :Long, quantity: Long){
        // get stock
        // 재고 감소
        // 저장

        val stock = stockRepository.findByWithOptimistic(id)
        stock.decrease(quantity)
        stockRepository.saveAndFlush(stock)
    }
}