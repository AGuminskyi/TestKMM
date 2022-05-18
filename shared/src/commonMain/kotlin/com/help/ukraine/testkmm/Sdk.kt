package com.help.ukraine.testkmm

import com.help.ukraine.testkmm.cache.Database
import com.help.ukraine.testkmm.cache.DatabaseDriverFactory
import com.help.ukraine.testkmm.data.entities.RocketLaunchEntity
import com.help.ukraine.testkmm.network.Api

class Sdk(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = Api()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunchEntity> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}
