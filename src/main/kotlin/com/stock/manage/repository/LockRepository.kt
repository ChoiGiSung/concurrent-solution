package com.stock.manage.repository

import com.stock.manage.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LockRepository : JpaRepository<Stock, Long> {

    @Query(value = "select get_lock(:key, 1000)", nativeQuery = true)
    fun getLock(key: String)


    @Query(value = "select release_lock(:key)", nativeQuery = true)
    fun releaseLock(key: String)
}