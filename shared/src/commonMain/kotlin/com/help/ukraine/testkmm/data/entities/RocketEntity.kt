package com.help.ukraine.testkmm.data.entities

import io.realm.RealmObject
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RocketEntity : RealmObject {
    @SerialName("rocket_id")
    var id: String = ""

    @SerialName("rocket_name")
    var name: String = ""

    @SerialName("rocket_type")
    var type: String = ""
}