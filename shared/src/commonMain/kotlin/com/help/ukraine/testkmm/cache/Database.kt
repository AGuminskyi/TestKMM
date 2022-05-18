package com.help.ukraine.testkmm.cache

import com.help.ukraine.testkmm.data.entities.LinksEntity
import com.help.ukraine.testkmm.data.entities.RocketEntity
import com.help.ukraine.testkmm.data.entities.RocketLaunchEntity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.query

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val configuration = RealmConfiguration.with(
        schema = setOf(
            LinksEntity::class,
            RocketEntity::class,
            RocketLaunchEntity::class,
        )
    )

    private val realm = Realm.open(configuration)

    internal suspend fun clearDatabase() {
        realm.write {
            query<RocketLaunchEntity>().find().also { delete(it) }
            query<RocketEntity>().find().also { delete(it) }
            query<LinksEntity>().find().also { delete(it) }
        }
    }

    internal suspend fun createLaunches(launches: List<RocketLaunchEntity>) {
        realm.write {
            launches.forEach { copyToRealm(it) }
        }
    }

    internal fun getAllLaunches(): List<RocketLaunchEntity> {
        return realm.query<RocketLaunchEntity>().find()
    }
}