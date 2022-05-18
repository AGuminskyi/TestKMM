package com.help.ukraine.testkmm.cache

import com.help.ukraine.testkmm.data.entities.LinksEntity
import com.help.ukraine.testkmm.data.entities.RocketEntity
import com.help.ukraine.testkmm.data.entities.RocketLaunchEntity

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val queries = database.appDatabaseQueries

    internal fun clearDatabase() {
        queries.transaction {
            queries.removeAllLaunches()
            queries.removeAllRockets()
        }
    }

    internal fun getAllLaunches(): List<RocketLaunchEntity> {
        return queries.selectAllLaunchesInfo(::mapLaunchSelecting).executeAsList()
    }

    internal fun createLaunches(launches: List<RocketLaunchEntity>) {
        queries.transaction {
            launches.forEach { launch ->
                val rocket = queries.selectRocketById(launch.rocketEntity.id).executeAsOneOrNull()
                if (rocket == null) {
                    insertRocket(launch)
                }

                insertLaunch(launch)
            }
        }
    }

    private fun insertRocket(launch: RocketLaunchEntity) {
        queries.insertRocket(
            id = launch.rocketEntity.id,
            name = launch.rocketEntity.name,
            type = launch.rocketEntity.type
        )
    }

    private fun insertLaunch(launch: RocketLaunchEntity) {
        queries.insertLaunch(
            flightNumber = launch.flightNumber.toLong(),
            missionName = launch.missionName,
            launchYear = launch.launchYear,
            rocketId = launch.rocketEntity.id,
            details = launch.details,
            launchSuccess = launch.launchSuccess ?: false,
            launchDateUTC = launch.launchDateUTC,
            missionPatchUrl = launch.linksEntity.missionPatchUrl,
            articleUrl = launch.linksEntity.articleUrl
        )
    }

    private fun mapLaunchSelecting(
        flightNumber: Long,
        missionName: String,
        launchYear: Int,
        rocketId: String,
        details: String?,
        launchSuccess: Boolean?,
        launchDateUTC: String,
        missionPatchUrl: String?,
        articleUrl: String?,
        rocket_id: String?,
        name: String?,
        type: String?
    ): RocketLaunchEntity {
        return RocketLaunchEntity(
            flightNumber = flightNumber.toInt(),
            missionName = missionName,
            launchYear = launchYear,
            details = details,
            launchDateUTC = launchDateUTC,
            launchSuccess = launchSuccess ?: false,
            rocketEntity = RocketEntity(
                id = rocketId,
                name = name!!,
                type = type!!
            ),
            linksEntity = LinksEntity(
                missionPatchUrl = missionPatchUrl ?: "",
                articleUrl = articleUrl
            )
        )
    }
}