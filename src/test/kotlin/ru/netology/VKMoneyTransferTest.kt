package ru.netology

import org.junit.Test
//import ru.netology.*
import kotlin.test.assertEquals

class VKMoneyTransfers1KtTest {

    @Test
    fun testMain() {
        main() // Просто проверяем, что main выполняется без ошибок
    }

    // Тесты для Maestro/Mastercard
    @Test
    fun testMaestro_StockTrue_NoCommission() {
        val result = calculateCommission("Maestro", 0.0, 50_000.0, true)
        assertEquals("1.0", result)
    }

    @Test
    fun testMaestro_StockFalse_BelowThreshold() {
        val result = calculateCommission("Maestro", 0.0, 50_000.0, false)
        assertEquals("20.0", result)
    }

    @Test
    fun testMaestro_StockFalse_AboveThreshold() {
        val result = calculateCommission("Maestro", 75_000.0, 10_000.0, false)
        assertEquals("80.0", result) // 10000*0.006 + 20 = 80
    }

    // Тесты для Visa/Мир
    @Test
    fun testVisa_SmallAmount_MinCommission() {
        val result = calculateCommission("Visa", 0.0, 1_000.0, false)
        assertEquals("35.0", result)
    }

    @Test
    fun testVisa_LargeAmount_Commission() {
        val result = calculateCommission("Visa", 0.0, 10_000.0, false)
        assertEquals("75.0", result) // 10000*0.0075 = 75
    }

    // Тесты для VK Pay
    @Test
    fun testVKPay_NoCommission() {
        val result = calculateCommission("VK Pay", 0.0, 10_000.0, false)
        assertEquals("0.0", result)
    }

    // Тесты на лимиты
    @Test
    fun testDailyLimitExceeded() {
        val result = calculateCommission("Maestro", 0.0, 150_001.0, false)
        assertEquals("Ошибка: превышен лимит перевода", result)
    }

    @Test
    fun testMonthlyLimitExceeded() {
        val result = calculateCommission("Maestro", 600_000.0, 1.0, false)
        assertEquals("Ошибка: превышен лимит перевода", result)
    }

    @Test
    fun testVKPayDailyLimitExceeded() {
        val result = calculateCommission("VK Pay", 0.0, 15_001.0, false)
        assertEquals("Ошибка: превышен лимит перевода", result)
    }

    @Test
    fun testVKPayMonthlyLimitExceeded() {
        val result = calculateCommission("VK Pay", 40_000.0, 1.0, false)
        assertEquals("Ошибка: превышен лимит перевода", result)
    }

    // Тест для неизвестного типа карты
    @Test
    fun testUnknownCardType() {
        val result = calculateCommission("Unknown", 0.0, 1_000.0, false)
        assertEquals("0.0", result) // В текущей реализации возвращает 0.0
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme