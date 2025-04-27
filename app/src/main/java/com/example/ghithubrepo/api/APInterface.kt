package com.example.ghithubrepo.api

import com.example.ghithubrepo.model.AccessTokenResponse
import com.example.ghithubrepo.model.GhithubRepoGetData
import com.example.ghithubrepo.model.RepositoryDeatialGetData
import com.example.ghithubrepo.model.RepositoryGetData
import com.example.ghithubrepo.model.RepositorySearcGetData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APInterface {


    @GET("user/repos")
    fun getRepositories(@Header("Authorization") token: String): Call<List<GhithubRepoGetData>>


    @FormUrlEncoded
    @POST("login/oauth/access_token")
    fun exchangeCodeForToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Call<AccessTokenResponse>

//    @GET("top/anime")
//    fun getAnimieList(): Call<GetAnimieListResponse?>?
//

    @GET("users/{username}/repos")
    fun getrepositoriesList(
        @Path("username") username: String
    ): Call<List<RepositoryGetData>>

    @GET("search/repositories")
    fun getserachrepositoriesList(
        @Query("q") query: String
    ): Call<RepositorySearcGetData>


    @GET("repos/{owner}/{repo}")
    fun getrepositoryDetails(
        @Path("owner") owner: String, @Path("repo") repo: String
    ): Call<RepositoryDeatialGetData>
}