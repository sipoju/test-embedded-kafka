package com.sipoju

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface TestProducer {

    @Topic("reservation")
    fun sendReservations(@KafkaKey key: String, value: Reservation)
}