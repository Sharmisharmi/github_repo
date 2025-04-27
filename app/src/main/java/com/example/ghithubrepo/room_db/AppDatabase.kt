package com.example.ghithubrepo.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RepoEntity::class, RepoDetailEntity::class, RepoSearchEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDAO
    abstract fun repoDetailDao(): RepoDetailDao
    abstract fun repoSearchDao(): RepoSearchDAO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "repository_database"
                )
                    .fallbackToDestructiveMigration() // This will allow clearing the old database when upgrading
                    .build().also { INSTANCE = it }
            }
        }
    }


}