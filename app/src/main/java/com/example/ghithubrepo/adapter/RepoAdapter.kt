package com.example.ghithubrepo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ghithubrepo.databinding.ItemRepoBinding
import com.example.ghithubrepo.model.ItemList
import com.example.ghithubrepo.model.RepositoryGetData
import com.example.ghithubrepo.model.RepositorySearcGetData
import com.example.ghithubrepo.view.RepositoryDetailActivity

class RepoAdapter(
    private val context: Context,
    private val items: List<RepositoryGetData> = emptyList(),
    private val itemsSearch: List<ItemList>? = emptyList(),
    private val from: String
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private val repo: List<Any>? = when (from) {
        "repos" -> items
        "search" -> itemsSearch
        else -> emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repo!![position])
    }

    override fun getItemCount(): Int = repo?.size ?: 0

    inner class RepoViewHolder(private val b: ItemRepoBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(repoItem: Any) {
            val name: String
            val description: String?
            val stars: Int
            val owner: String

            when (repoItem) {
                is RepositoryGetData -> {
                    name = repoItem.name.toString()
                    description = repoItem.description?.toString()
                    stars = repoItem.stargazersCount ?: 0
                    owner = repoItem.owner?.login ?: "unknown"
                }

                is ItemList -> {
                    name = repoItem.name.toString()
                    description = repoItem.description?.toString()
                    stars = repoItem.stargazersCount ?: 0
                    owner = repoItem.owner?.login ?: "unknown"
                }

                else -> return
            }

            b.tvName.text = name
            b.tvDescription.text = description ?: "No description"
            b.starTv.text = stars.toString()

            b.repoLl.setOnClickListener {
                val intent = Intent(context, RepositoryDetailActivity::class.java).apply {
                    putExtra("OWNER", owner)
                    putExtra("REPO", name)
                }
                context.startActivity(intent)
            }
        }
    }
}
