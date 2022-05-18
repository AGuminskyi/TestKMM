package com.help.ukraine.testkmm.network

import com.help.ukraine.testkmm.data.entities.RocketLaunchEntity
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json

private const val LAUNCHES_ENDPOINT = "https://api.spacexdata.com/v3/launches"

private val httpClient = HttpClient {
    install(JsonFeature) {
        val json = Json { ignoreUnknownKeys = true }
        serializer = KotlinxSerializer(json)
    }
}

class Api {

    suspend fun getAllLaunches(): List<RocketLaunchEntity> {
        return httpClient.get(LAUNCHES_ENDPOINT)
    }

    suspend fun getAllLaunchesResponse(): HttpResponse {
        return httpClient.get(LAUNCHES_ENDPOINT) {
//            this.
        }
    }
}