package com.example.ghithubrepo.model

data class GhithubRepoGetData( val id: Int,
                               val name: String,
                               val description: String,
                               val html_url: String
)

data class AccessTokenResponse(
    val access_token: String
)
