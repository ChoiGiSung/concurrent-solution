package com.stock.manage.facade

import com.stock.manage.repository.LockRepository
import com.stock.manage.service.StockService
import org.springframework.stereotype.Service

@Service
class NameLockStockFacade(
    private val stockService: StockService,
    private val lockRepository: LockRepository
) {

    fun decrease(id: Long, quantity: Long) {
        try {
            lockRepository.getLock(id.toString())
            stockService.decrease(id,quantity)
        }finally {
            lockRepository.releaseLock(id.toString())
        }
    }
}