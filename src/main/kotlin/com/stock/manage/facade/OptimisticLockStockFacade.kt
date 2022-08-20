package com.stock.manage.facade

import com.stock.manage.service.OptimisticStockService
import org.springframework.stereotype.Service

@Service
class OptimisticLockStockFacade(
    private val optimisticStockService: OptimisticStockService
) {

    fun decrease(id: Long, quantity: Long){
        while (true){
            try {
                optimisticStockService.decrease(id,quantity)
                break
            }catch (e: Exception){
                Thread.sleep(50)
            }
        }
    }
}