package com.help.ukraine.testkmm.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketEntity(
    @SerialName("rocket_id")
    val id: String,
    @SerialName("rocket_name")
    val name: String,
    @SerialName("rocket_type")
    val type: String
)