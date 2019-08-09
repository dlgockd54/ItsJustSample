package com.example.itsjustsample.model.source

import com.example.githubapisample.model.GithubRepo
import com.example.githubapisample.model.User
import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by hclee on 2019-08-09.
 */

interface GithubApi {
    @GET("search/users")
    fun getGithubUserInfoSingle(
        @Query("q") userName: String
    ): Single<JsonElement>

    @GET("users/{username}/repos")
    fun getGithubUserRepoListSingle(
        @Path("username") userName: String
    ): Single<List<GithubRepo>>
}