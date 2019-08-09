package com.example.itsjustsample.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapisample.model.GithubRepo
import com.example.githubapisample.model.User
import com.example.itsjustsample.R
import com.example.itsjustsample.model.ListItem
import kotlinx.android.synthetic.main.item_github_repo.view.*

/**
 * Created by hclee on 2019-08-09.
 */

class GithubRepoAdapter(val mActivity: Activity, val mGithubRepoList: MutableList<ListItem>) :
    RecyclerView.Adapter<GithubRepoAdapter.GithubRepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder =
        GithubRepoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_github_repo, parent, false)
        )

    override fun getItemCount(): Int = mGithubRepoList.size

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        with(holder) {
            mOwnerAvatarImageView.visibility = View.GONE
            mOwnerNameText.visibility = View.GONE

            with(mGithubRepoList[position]) {
                if(this is GithubRepo) {
                    mRepoNameTextView.text = this.name
                    mStarCountTextView.text = this.stargazersCount.toString()
                    mRepoDescriptionTextView.text = this.description
                } else {
                    Glide.with(mActivity)
                        .load((mGithubRepoList[position] as User).avatar_url)
                        .into(mOwnerAvatarImageView)
                    mOwnerNameText.text = (mGithubRepoList[position] as User).login

                    mOwnerAvatarImageView.visibility = View.VISIBLE
                    mOwnerNameText.visibility = View.VISIBLE
                    mRepoNameTextView.visibility = View.GONE
                    mStarCountTextView.visibility = View.GONE
                    mRepoDescriptionTextView.visibility = View.GONE
                }
            }
        }
    }

    class GithubRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mRepoNameTextView: TextView = itemView.tv_github_repo_name
        val mStarCountTextView: TextView = itemView.tv_start_count
        val mRepoDescriptionTextView: TextView = itemView.tv_github_repo_description
        val mOwnerAvatarImageView: ImageView = itemView.iv_avatar
        val mOwnerNameText: TextView = itemView.tv_owner_name
    }
}