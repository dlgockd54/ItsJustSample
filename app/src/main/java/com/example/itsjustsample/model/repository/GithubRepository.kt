package com.example.itsjustsample.model.repository

import com.example.githubapisample.model.GithubRepo
import com.example.githubapisample.model.User
import com.example.itsjustsample.model.source.ApiManager
import com.example.itsjustsample.model.source.GithubApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by hclee on 2019-08-09.
 */

object GithubRepository {
    private val api: GithubApi = ApiManager.githubApi

    fun getGithubUserInfo(userName: String) : Single<List<User>> =
        api.getGithubUserInfoSingle(userName)
            .map {
                it.asJsonObject.getAsJsonArray("items")
                    .map {
                        ApiManager.gson.fromJson(it, User::class.java)
                    }
            }


    fun getGithubUserRepoListSingle(userName: String) : Single<List<GithubRepo>> =
        api.getGithubUserRepoListSingle(userName)
            .subscribeOn(Schedulers.io())
}