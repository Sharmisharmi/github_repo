package com.example.ghithubrepo.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepoEntity(@PrimaryKey (autoGenerate = true) val id:Int = 0,
                      val name:String?,
                      val owner:String?,
                      val description: String?,
                      val language: String?,
                      val stargazersCount: Int?,
                      val forksCount: Int?)


@Entity(
    tableName = "repository_detail",
    primaryKeys = ["owner", "name"] // Composite primary key
)
data class RepoDetailEntity(
    val owner: String,  // Part of composite key
    val name: String,   // Part of composite key
    val description: String?,
    val language: String?,
    val stargazersCount: Int?,
    val forksCount: Int?,
    val contributors: String?,
    val issuesCount: Int?,
    val lastUpdated: String?
)

@Entity(tableName = "repository_search")
data class RepoSearchEntity(@PrimaryKey (autoGenerate = true) val id:Int = 0,
                            val query: String,
                            val name:String?,
                            val owner:String?,
                            val description: String?,
                            val language: String?,
                            val stargazersCount: Int?,
                            val forksCount: Int?)
