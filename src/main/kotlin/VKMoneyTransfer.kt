package ru.netology

fun main() {

    println(calculateCommission("Maestro", 0.0, 50_000.0, false))
    // println(calculateCommission("VK Pay", 0.0, 15_001.0, false))
}
fun calculateCommission(
    typeCard: String,
    monthlyTransferred: Double = 0.0, //переводы за месяц
    transferAmount: Double,  //фактический перевод
    stock: Boolean,
): String {
    val dailyLimit = 150_000.0 //дневной лимит
    val monthlyLimitSending = 600_000.0 //месячный лимит перевод
    val monthlyLimitReceiving = 600_000.0  //месячный лимит получение
    val newTotal = monthlyTransferred + transferAmount
    val VKdailyLimit = 15_000.0
    val VKmonthlyLimit = 40_000.0

//    проверяем на превышение лимитов
    if (transferAmount > dailyLimit || newTotal > monthlyLimitSending) {
        return "Ошибка: превышен лимит перевода"
    }
    if (typeCard == "VK Pay") {
        if (transferAmount > VKdailyLimit || newTotal > VKmonthlyLimit) {
            return "Ошибка: превышен лимит перевода"
        }
    }

    var commission = 0.0
    if (stock) {
        when (typeCard) {
            "Mastercard", "Maestro" -> {
                if (newTotal <= 75_000.0 && newTotal > 300.0) {
                    commission = 0.0
                    return "$commission"
//                    return "Операция выполнена. Карта: \"$typeCard\" \nСумма перевода: $transferAmount \nКомиссия: $commission, руб"
                }
            }
        }
    }

    when (typeCard) {
        "Mastercard", "Maestro" -> {
            when {
                monthlyTransferred >= 75_000.0 -> {
                    val taxableAmount = transferAmount
                    commission = Math.max(taxableAmount * 0.006, 0.0) + 20.0
                }
                else -> {
                    val remainingExemptAmount = 75_000.0 - monthlyTransferred
                    val taxableAmount = Math.max(transferAmount - remainingExemptAmount, 0.0)
                    commission = Math.max(taxableAmount * 0.006, 0.0) + 20.0
                }
            }

        }

        "Visa", "Мир" -> {
            val visaCommission = transferAmount * 0.0075
            commission = Math.max(visaCommission, 35.0)
        }

        "VK Pay" -> {}
    }
    return "$commission"
//    return "Операция выполнена. Карта: \"$typeCard\" \nСумма перевода: $transferAmount \nКомиссия: $commission, руб"
}