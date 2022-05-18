package com.help.ukraine.testkmm.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksEntity(
    @SerialName("mission_patch")
    val missionPatchUrl: String?,
    @SerialName("article_link")
    val articleUrl: String?,
)