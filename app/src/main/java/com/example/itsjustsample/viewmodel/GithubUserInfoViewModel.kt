package com.example.githubapisample.viewmodel

import com.example.githubapisample.model.User
import com.example.itsjustsample.model.repository.GithubRepository
import com.example.itsjustsample.viewmodel.BaseViewModel
import io.reactivex.Single

/**
 * Created by hclee on 2019-08-09.
 */

class GithubUserInfoViewModel : BaseViewModel() {
    fun getUGithubUserInfo(userName: String) : Single<List<User>> =
        GithubRepository.getGithubUserInfo(userName)
}