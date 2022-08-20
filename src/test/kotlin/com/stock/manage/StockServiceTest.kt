package com.stock.manage

import com.stock.manage.domain.Stock
import com.stock.manage.repository.StockRepository
import com.stock.manage.service.StockService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StockServiceTest {

    @Autowired
    lateinit var stockService: StockService

    @Autowired
    lateinit var stockRepository: StockRepository

    @BeforeEach
    fun before(){
        val stock = Stock(1L,100L)
        stockRepository.saveAndFlush(stock)
    }

    @AfterEach
    fun after(){
        stockRepository.deleteAll()
    }

    @Test
    fun `재고 감소`(){
        stockService.decrease(1L,1L)

        val stock = stockRepository.findById(1L).orElseThrow()

        assertThat(stock.quantity).isEqualTo(99)
    }
}