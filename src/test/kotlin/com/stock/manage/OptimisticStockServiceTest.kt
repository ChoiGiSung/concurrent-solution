package com.stock.manage

import com.stock.manage.domain.Stock
import com.stock.manage.facade.OptimisticLockStockFacade
import com.stock.manage.repository.StockRepository
import com.stock.manage.service.PessimisticStockService
import com.stock.manage.service.StockService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class OptimisticStockServiceTest {

    @Autowired
    lateinit var stockService: OptimisticLockStockFacade

    @Autowired
    lateinit var stockRepository: StockRepository

    @BeforeEach
    fun before() {
        val stock = Stock(1L, 100L)
        stockRepository.saveAndFlush(stock)
    }

    @AfterEach
    fun after() {
        stockRepository.deleteAll()
    }

    @Test
    fun `재고 감소`() {
        stockService.decrease(1L, 1L)

        val stock = stockRepository.findById(1L).orElseThrow()

        assertThat(stock.quantity).isEqualTo(99)
    }

    @Test
    fun `동시에 100개의 요청`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(32)
        val countDouLatch = CountDownLatch(threadCount)

        for (i in 1..threadCount) {
            executorService.submit {
                try {
                    stockService.decrease(1L, 1L)
                }finally {
                    countDouLatch.countDown()
                }
                
            }
        }

        countDouLatch.await()

        val stock = stockRepository.findById(1L).orElseThrow()

        assertThat(stock.quantity).isEqualTo(0L)
    }
}