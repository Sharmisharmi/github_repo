package com.example.ghithubrepo.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface RepoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repo: List<RepoEntity>)

    @Query("SELECT * FROM repository")
    fun getAllRepos(): List<RepoEntity>

    @Query("DELETE FROM repository")
    fun deleteRepos()
}


@Dao
interface RepoSearchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchRepos(repo: List<RepoSearchEntity>?)

    @Query("SELECT * FROM repository_search WHERE `query` = :query")
    fun getSearchRepos(query: String): List<RepoSearchEntity>

    @Query("DELETE FROM repository_search")
    fun deleteSearchRepos()
}

@Dao
interface RepoDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepoDetail(repoDetail: List<RepoDetailEntity>)

    @Query("SELECT * FROM repository_detail WHERE owner = :owner AND name = :name")
    fun getRepoDetail(owner: String, name: String): RepoDetailEntity?
}
