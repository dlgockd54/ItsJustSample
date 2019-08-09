package com.example.itsjustsample.viewmodel

import com.example.githubapisample.model.GithubRepo
import com.example.itsjustsample.model.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by hclee on 2019-08-09.
 */

class GithubUserRepoViewModel : BaseViewModel() {
    fun getUserRepoList(userName: String) : Single<List<GithubRepo>> =
        GithubRepository.getGithubUserRepoListSingle(userName)
            .subscribeOn(Schedulers.io())
}