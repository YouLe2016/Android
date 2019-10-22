package com.wyl.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.wyl.recyclerview.databinding.ItemListAdapterBinding
import io.ditclear.bindingadapter.BindingViewHolder
import kotlinx.android.synthetic.main.activity_list_adapter.*

/**
 * ListAdapter的用法
 */
class ListAdapterActivity : AppCompatActivity() {
    private var data = List(10) {
        User(it, "Name$it", 90f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_adapter)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        userAdapter.submitList(data)

        btRefresh.setOnClickListener {
            val list = List(10) {
                User(it, "Name$it", 90f)
            }
            userAdapter.submitList(list)
        }

        btLoadMore.setOnClickListener {
            val list = List(10) {
                val i = it + data.size
                User(i, "Name$i", 80.0f)
            }
            val newList = data + list
            data = newList
            userAdapter.submitList(newList)
        }

    }


    val diffUtilCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    private val userAdapter = object :
        ListAdapter<User, BindingViewHolder<ItemListAdapterBinding>>(diffUtilCallback) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BindingViewHolder<ItemListAdapterBinding> {
            val binding =
                ItemListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BindingViewHolder(binding)
        }

        override fun onBindViewHolder(
            holder: BindingViewHolder<ItemListAdapterBinding>,
            position: Int
        ) {
            holder.binding.user = getItem(position)
        }
    }
}

data class User(var id: Int, var name: String, var score: Float)