package com.example.itsjustsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapisample.model.GithubRepo
import com.example.githubapisample.model.User
import com.example.githubapisample.viewmodel.GithubUserInfoViewModel
import com.example.itsjustsample.R
import com.example.itsjustsample.view.adapter.GithubRepoAdapter
import com.example.itsjustsample.viewmodel.GithubUserRepoViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    companion object {
        val TAG: String = UserActivity::class.java.simpleName
    }

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mGithubRepoAdapter: GithubRepoAdapter
    private lateinit var mGithubUserInfoViewModel: GithubUserInfoViewModel
    private lateinit var mGithubUserRepoViewModel: GithubUserRepoViewModel
    private lateinit var mOwner: User
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        init()

        mUserName = intent.data?.getQueryParameter("repos")

        mUserName?.let {
            pullGithubUserInfo(it)
        }
    }

    private fun init() {
        mGithubRepoAdapter = GithubRepoAdapter(this@UserActivity, mutableListOf())
        mGithubUserInfoViewModel = ViewModelProviders.of(this@UserActivity).get(GithubUserInfoViewModel::class.java)
        mGithubUserRepoViewModel = ViewModelProviders.of(this@UserActivity).get(GithubUserRepoViewModel::class.java)

        rv_repo_list.apply {
            adapter = mGithubRepoAdapter
            layoutManager = LinearLayoutManager(this@UserActivity)
        }
    }

    private fun pullGithubUserInfo(userName: String) {
        mCompositeDisposable.add(
            mGithubUserInfoViewModel.getUGithubUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it[0]
                }
                .subscribeWith(object : DisposableSingleObserver<User>() {
                    override fun onSuccess(t: User) {
                        with(mGithubRepoAdapter) {
                            mOwner = t

                            mUserName?.let {
                                pullGithubUserRepo(it)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    private fun pullGithubUserRepo(userName: String) {
        mCompositeDisposable.add(
            mGithubUserRepoViewModel.getUserRepoList(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GithubRepo>>() {
                    override fun onSuccess(t: List<GithubRepo>) {
                        with(mGithubRepoAdapter) {
                            mGithubRepoList.clear()
                            mGithubRepoList.addAll(t)
                            mGithubRepoList.add(0, mOwner)
                            notifyDataSetChanged()
                        }
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }
}
