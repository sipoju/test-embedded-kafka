package com.sipoju

import io.micronaut.configuration.kafka.config.AbstractKafkaConfiguration
import io.micronaut.context.ApplicationContext
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class TestEmbeddedKafkaTest {

    @Test
    fun `Publish and receive message`() {

        val applicationContext = ApplicationContext.run(mapOf(
                AbstractKafkaConfiguration.EMBEDDED to true,
                AbstractKafkaConfiguration.EMBEDDED_TOPICS to "reservation"
        ))

        val producer = applicationContext.getBean(TestProducer::class.java)
        val listener =  applicationContext.getBean(ReservationListener::class.java)

        val reservation = Reservation("1", "Reservation")
        producer.sendReservations(reservation.id, reservation)

        await().atMost(10, TimeUnit.SECONDS).until { listener.data == reservation }
    }

}