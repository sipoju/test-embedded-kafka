package com.sipoju

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Single
import org.apache.kafka.common.serialization.Deserializer

@Suppress("unused")
class JsonNodeDeserializer : Deserializer<Single<JsonNode>> {

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun deserialize(topic: String?, data: ByteArray?): Single<JsonNode> = Single.fromCallable { ObjectMapper().readTree(data) }

    override fun close() {}
}