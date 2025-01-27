package com.shieldteq.payment

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<PaymentApplication>().with(TestcontainersConfiguration::class).run(*args)
}
