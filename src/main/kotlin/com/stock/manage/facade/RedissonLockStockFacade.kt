package com.stock.manage.facade

import com.stock.manage.service.StockService
import jodd.time.TimeUtil
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

@Component
class RedissonLockStockFacade(
    private val redissonClient: RedissonClient,
    private val stockService: StockService
) {

    fun decrease(key: Long, quantity: Long) {
        val lock = redissonClient.getLock(key.toString())

        try {
            val available = lock.tryLock(10, 1, TimeUnit.SECONDS)

            if(!available){
                println("락 획득 실패")
                return
            }

            stockService.decrease(key, quantity)
        }catch (e: Exception){
            throw RuntimeException()
        }finally {
            lock.unlock()
        }
    }
}