package com.help.ukraine.testkmm.data.entities

import io.realm.RealmObject
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LinksEntity : RealmObject {
    @SerialName("mission_patch")
    var missionPatchUrl: String? = ""

    @SerialName("article_link")
    var articleUrl: String? = ""
}
