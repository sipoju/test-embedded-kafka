package com.sipoju

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import io.reactivex.Single
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

@KafkaListener(groupId = "reservations")
class ReservationListener {

    private val log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    var data: Reservation? = null

    @Topic("reservation")
    fun receive(
        @KafkaKey id: String,
        reservationFlowable: Single<JsonNode>
    ): Single<Reservation> {
        log.info("Got Reservation - $id")
        return reservationFlowable
                .map {
                    data = ObjectMapper().treeToValue<Reservation>(it, Reservation::class.java)
                    data!!
                }
                .doOnSuccess { log.debug("Successfully processed Reservation - $id") }
    }
}

