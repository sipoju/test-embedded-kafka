package com.sipoju

import com.fasterxml.jackson.annotation.JsonProperty

data class Reservation(@JsonProperty("id")val id: String, @JsonProperty("name") val name: String)